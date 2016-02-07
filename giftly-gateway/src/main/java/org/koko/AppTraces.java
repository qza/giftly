package org.koko;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AppTraces
 */
@Configuration
@ConditionalOnExpression("${spring.zipkin.enabled:true}")
public class AppTraces {

    @Bean
    public AlwaysSampler alwaysSampler() {
        return new AlwaysSampler();
    }

}
