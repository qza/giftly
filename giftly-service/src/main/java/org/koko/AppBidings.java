package org.koko;

import org.koko.gift.GiftInputChannel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/**
 * AppBidings
 */
@Configuration
@EnableBinding(GiftInputChannel.class)
@ConditionalOnExpression("${kafka.enabled:true}")
public class AppBidings {
}
