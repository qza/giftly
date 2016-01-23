package org.koko.gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * GiftEndpoint
 */
@MessageEndpoint
public class GiftEndpoint {

    @Autowired
    GiftRepository giftRepository;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void addGift(String name) {
        giftRepository.save(new Gift(name));
    }

}