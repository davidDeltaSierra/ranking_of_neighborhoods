package br.com.ranking_of_neighborhoods.common;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import static org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository.DEFAULT_SPRING_SECURITY_CONTEXT_ATTR_NAME;

public class SecurityFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return getToken(exchange)
                .flatMap(token ->
                        getSecurityContext(exchange.getSession())
                                .map(securityContext -> {
                                    setAuthentication(securityContext, token);
                                    return securityContext;
                                })
                )
                .then(chain.filter(exchange));
    }

    private void setAuthentication(SecurityContext securityContext, String token) {
        securityContext.setAuthentication(
                new UsernamePasswordAuthenticationToken(token, null, null)
        );
    }

    private Mono<SecurityContext> getSecurityContext(Mono<WebSession> session) {
        return ReactiveSecurityContextHolder.getContext()
                .switchIfEmpty(session.map(it -> {
                    var context = new SecurityContextImpl();
                    it.getAttributes().put(DEFAULT_SPRING_SECURITY_CONTEXT_ATTR_NAME, context);
                    return context;
                }));
    }

    private Mono<String> getToken(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().get("Authorization"))
                .flatMap(it -> Mono.justOrEmpty(it.stream().findFirst()));
    }
}
