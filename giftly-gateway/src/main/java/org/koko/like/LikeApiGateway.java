package org.koko.like;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

/**
 * LikeApiGateway
 */
@RestController
@RequestMapping("/likes")
public class LikeApiGateway {

    @Autowired
    @Output(LikeOutputChannel.NAME)
    private MessageChannel messageChannel;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void writeLike(@RequestBody Like like) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter likeString = new StringWriter();
        mapper.writeValue(likeString, like);
        messageChannel.send(MessageBuilder.withPayload(likeString).build());
    }

}
