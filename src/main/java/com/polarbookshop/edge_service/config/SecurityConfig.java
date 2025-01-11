package com.polarbookshop.edge_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
                                                     ReactiveClientRegistrationRepository clientRegistrationRepository) {
        return http.
                authorizeExchange(exchange -> exchange.anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .logout(logoutSpec -> logoutSpec.logoutSuccessHandler(
                        oidcLogoutSuccessHandler(clientRegistrationRepository)))
                .build();
    }

    private ServerLogoutSuccessHandler oidcLogoutSuccessHandler(
            ReactiveClientRegistrationRepository clientRegistrationRepository
    ) {
        var oidcLogoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(
                (ClientRegistrationRepository) clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}");
        return (ServerLogoutSuccessHandler) oidcLogoutSuccessHandler;
    }
}
