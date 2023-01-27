package br.com.ranking_of_neighborhoods.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.web.server.SecurityWebFiltersOrder.HTTP_BASIC;

@Configuration
class SecurityConfig {
    @Bean
    WebSecurityCustomizer webSecurity() {
        return web -> web.ignoring()
                .requestMatchers(HttpMethod.OPTIONS, "/**")
                .requestMatchers("/public/**");
    }

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                .cors().disable()
                .authorizeExchange()
                .anyExchange()
                .permitAll()
                .and()
                .addFilterAt(new SecurityFilter(), HTTP_BASIC)
                .build();
    }
}
