package org.koko.gift;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * GiftInputChannel
 */
public interface GiftInputChannel {

    String NAME = "gifts";

    @Input(GiftInputChannel.NAME)
    MessageChannel input();

}