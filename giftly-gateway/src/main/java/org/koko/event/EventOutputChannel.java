package org.koko.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import org.springframework.cloud.stream.annotation.Bindings;
/**
 * EventOutputChannel
 */
public interface EventOutputChannel {

    String NAME = "events";

    @Output(EventOutputChannel.NAME)
    MessageChannel output();

}
