package io.authu.spring.jwt.webflux;

import io.authu.spring.jwt.core.AuthuJwtPrefix;
import io.authu.spring.jwt.core.JwtAuthServer;
import io.authu.spring.jwt.core.request.JwtPathMatcher;
import io.authu.spring.jwt.core.request.JwtRequestProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.RequiredTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/9/27.
 */
@Slf4j
@Configuration
@ConditionalOnClass({WebFluxConfigurer.class})
@ConditionalOnProperty(prefix = AuthuJwtPrefix.CORE, name = {"enabled","request.filter-enabled"}, havingValue = "true", matchIfMissing = true)
public class AuthuJwtWebfluxAutoConfiguration implements WebFilter {

    @Resource
    private JwtAuthServer authServer;
    @Resource
    private JwtRequestProperties requestProperties;

    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String path = request.getPath().value();
        log.info(path);
        if (JwtPathMatcher.excludeMatch(path)) {
            return chain.filter(exchange);
        }

        List<String> list = request.getHeaders().get(requestProperties.getHeaderName());
        if (list == null || list.size() != 1) {
            log.warn("Token is empty!");
            throw new RequiredTypeException("Token is empty!");
        }
        String token = list.get(0);
        Jws<Claims> claims = authServer.parse(token);
        Map<String, Object> attributes = exchange.getAttributes();
        attributes.put(requestProperties.getAttributeName(), claims);

        return chain.filter(exchange);
    }

}
