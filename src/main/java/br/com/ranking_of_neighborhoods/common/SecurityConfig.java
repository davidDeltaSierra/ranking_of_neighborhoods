package br.com.ranking_of_neighborhoods.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.web.server.SecurityWebFiltersOrder.AUTHENTICATION;

@Configuration
class SecurityConfig {
    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                .cors().disable()
                .authorizeExchange().pathMatchers("/public/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .addFilterAt(new SecurityFilter(), AUTHENTICATION)
                .build();
    }
}
