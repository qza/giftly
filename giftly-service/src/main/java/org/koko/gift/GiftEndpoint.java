package org.koko.gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * GiftEndpoint
 */
@MessageEndpoint
public class GiftEndpoint {

    @Autowired
    GiftRepository giftRepository;

    @ServiceActivator(inputChannel = GiftInputChannel.NAME)
    public void addGift(String name) {
        giftRepository.save(new Gift(name));
    }

}