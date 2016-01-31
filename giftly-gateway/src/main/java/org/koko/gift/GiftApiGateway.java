package org.koko.gift;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;

import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gifts")
public class GiftApiGateway {

    @Autowired
    @LoadBalanced
    public RestTemplate restTemplate;

    @Autowired
    @Output(GiftOutputChannel.NAME)
    private MessageChannel messageChannel;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addGift(@RequestBody Gift gift) {
        messageChannel.send(MessageBuilder.withPayload(gift.getName()).build());
    }

    @HystrixCommand(fallbackMethod = "getGiftNamesAlternative")
    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public Collection<String> getGiftNames() {
        ParameterizedTypeReference<Resources<Gift>> resources = new ParameterizedTypeReference<Resources<Gift>>() {};
        ResponseEntity<Resources<Gift>> responseEntity =
                restTemplate.exchange("http://giftly-service/gifts", HttpMethod.GET, null, resources);
        return responseEntity.getBody().getContent().stream().map(Gift::getName).collect(Collectors.toList());
    }

    public Collection<String> getGiftNamesAlternative() {
        return Collections.emptyList();
    }

}
