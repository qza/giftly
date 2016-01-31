package org.koko.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * EventApiGateway
 */
@RestController
@RequestMapping("/events")
public class EventApiGateway {

    @Autowired
    @Output(EventOutputChannel.NAME)
    private MessageChannel messageChannel;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void writeEvent(@RequestBody Event event) {
        messageChannel.send(MessageBuilder.withPayload(event).build());
    }

}
