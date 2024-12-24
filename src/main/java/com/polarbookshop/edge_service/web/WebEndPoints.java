package com.polarbookshop.edge_service.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class WebEndPoints {

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/catalog-fallback", request ->
                        ServerResponse.ok().body(Mono.just("")))
                .POST("/catalog-fallback", request ->
                        ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build())
                .build();
    }
}
