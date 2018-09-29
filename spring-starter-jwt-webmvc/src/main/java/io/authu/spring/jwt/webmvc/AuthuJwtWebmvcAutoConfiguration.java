package io.authu.spring.jwt.webmvc;

import io.authu.spring.jwt.core.AuthuJwtPrefix;
import io.authu.spring.jwt.core.JwtAuthServer;
import io.authu.spring.jwt.core.request.JwtPathMatcher;
import io.authu.spring.jwt.core.request.JwtRequestProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
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
@Slf4j
@Configuration
@ConditionalOnClass({OncePerRequestFilter.class, FilterChain.class})
@ConditionalOnProperty(prefix = AuthuJwtPrefix.CORE, name = {"enabled","request.filter-enabled"}, havingValue = "true", matchIfMissing = true)
public class AuthuJwtWebmvcAutoConfiguration extends OncePerRequestFilter {

    @Resource
    private JwtAuthServer authServer;
    @Resource
    private JwtRequestProperties requestProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String servletPath = request.getServletPath();
        log.info(servletPath);
        if (JwtPathMatcher.excludeMatch(servletPath)) {
            filterChain.doFilter(request,response);
            return;
        }

        String header = request.getHeader(requestProperties.getHeaderName());
        Jws<Claims> claims = authServer.parse(header);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            requestAttributes.setAttribute(requestProperties.getAttributeName(), claims, RequestAttributes.SCOPE_REQUEST);
        }
        filterChain.doFilter(request,response);
    }

}
