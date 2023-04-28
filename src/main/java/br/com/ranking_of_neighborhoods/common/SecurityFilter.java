package br.com.ranking_of_neighborhoods.common;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static org.springframework.security.core.context.ReactiveSecurityContextHolder.withSecurityContext;

public class SecurityFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
                .contextWrite(context -> withSecurityContext(
                        auth(exchange.getRequest().getHeaders())
                ));
    }

    private Mono<SecurityContext> auth(HttpHeaders headers) {
        return Mono.justOrEmpty(headers.get("Authorization"))
                .flatMap(it -> Mono.justOrEmpty(it.stream().findFirst()))
                .map(token -> new SecurityContextImpl(
                                new UsernamePasswordAuthenticationToken("username", token, null)
                        )
                );
    }
}
