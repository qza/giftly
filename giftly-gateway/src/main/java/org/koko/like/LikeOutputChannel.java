package org.koko.like;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * LikeOutputChannel
 */
public interface LikeOutputChannel {

    String NAME = "likes";

    @Output(LikeOutputChannel.NAME)
    MessageChannel output();

}
