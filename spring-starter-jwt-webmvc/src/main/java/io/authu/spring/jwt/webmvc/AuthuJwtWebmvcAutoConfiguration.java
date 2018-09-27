package io.authu.spring.jwt.webmvc;

import io.authu.spring.jwt.core.AuthuJwtPrefix;
import io.authu.spring.jwt.core.JwtAuthServer;
import io.authu.spring.jwt.core.JwtProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/9/27.
 */
@Configuration
@ConditionalOnClass({OncePerRequestFilter.class, FilterChain.class})
@ConditionalOnProperty(prefix = AuthuJwtPrefix.CORE, name = "enabled", havingValue = "true", matchIfMissing = true)
public class AuthuJwtWebmvcAutoConfiguration extends OncePerRequestFilter {

    @Resource
    private JwtAuthServer authServer;
    @Resource
    private JwtProperties properties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(properties.getHeader());
        authServer.parse(header);
        filterChain.doFilter(request,response);
    }

}
