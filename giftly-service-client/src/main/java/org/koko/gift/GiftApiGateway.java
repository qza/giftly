package org.koko.gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
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

import java.math.BigInteger;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gifts")
public class GiftApiGateway {

    @Autowired
    @LoadBalanced
    public RestTemplate restTemplate;

    @Autowired
    @Output(Source.OUTPUT)
    private MessageChannel messageChannel;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addGift(@RequestBody Gift gift) {
        messageChannel.send(MessageBuilder.withPayload(gift.getName()).build());
    }

    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public Collection<String> getGiftNames() {
        ParameterizedTypeReference<Resources<Gift>> resources = new ParameterizedTypeReference<Resources<Gift>>() { };
        ResponseEntity<Resources<Gift>> responseEntity =
                restTemplate.exchange("http://giftly-service/gifts", HttpMethod.GET, null, resources);
        return responseEntity.getBody().getContent().stream().map(Gift::getName).collect(Collectors.toList());
    }

}

class Gift {

    private BigInteger id;
    private String name;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
