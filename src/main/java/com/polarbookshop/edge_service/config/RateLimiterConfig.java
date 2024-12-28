package com.polarbookshop.edge_service.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimiterConfig {

    public KeyResolver keyResolver() {
        return exchange -> Mono.just("anonymous");
    }
}
