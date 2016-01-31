package org.koko.gift;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * GiftOutputChannel
 */
public interface GiftOutputChannel {

    String NAME = "gifts";

    @Output(GiftOutputChannel.NAME)
    MessageChannel output();

}