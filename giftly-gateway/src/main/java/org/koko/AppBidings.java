package org.koko;

import org.koko.gift.GiftOutputChannel;
import org.koko.like.LikeOutputChannel;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/**
 * AppBidings
 */
@Configuration
@EnableBinding({GiftOutputChannel.class, LikeOutputChannel.class})
@ConditionalOnExpression("${kafka.enabled:true}")
public class AppBidings {
}
