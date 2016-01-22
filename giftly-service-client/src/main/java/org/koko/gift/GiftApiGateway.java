package org.koko.gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@RestController
public class GiftApiGateway {

    @Autowired
    @LoadBalanced
    public RestTemplate restTemplate;

    @RequestMapping("/gifts")
    public Collection<String> getGiftNames() {
        ParameterizedTypeReference<Resources<String>> resources = new ParameterizedTypeReference<Resources<String>>() {};
        ResponseEntity<Resources<String>> responseEntity =
                restTemplate.exchange("http://giftly-service/gifts", HttpMethod.GET, null, resources);
        return responseEntity.getBody().getContent();
    }

}
