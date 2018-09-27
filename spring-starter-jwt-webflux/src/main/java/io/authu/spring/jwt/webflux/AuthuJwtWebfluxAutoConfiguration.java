package io.authu.spring.jwt.webflux;

import io.authu.spring.jwt.core.JwtAuthServer;
import io.authu.spring.jwt.core.JwtProperties;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/9/27.
 */
@Slf4j
@Configuration
@ConditionalOnClass({WebFilter.class, Mono.class})
public class AuthuJwtWebfluxAutoConfiguration implements WebFilter {

    @Resource
    private JwtAuthServer authServer;
    @Resource
    private JwtProperties properties;

    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        List<String> list = request.getHeaders().get(properties.getHeader());
        if (list == null || list.size() != 1) {
            log.warn("Token is empty!");
            throw new UnsupportedJwtException("Token is empty!");
        }
        String token = list.get(0);
        authServer.parse(token);

        return chain.filter(exchange);
    }

}
