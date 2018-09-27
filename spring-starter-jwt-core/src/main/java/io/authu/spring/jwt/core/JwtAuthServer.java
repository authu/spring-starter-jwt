package io.authu.spring.jwt.core;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/9/26.
 */
@Slf4j
public class JwtAuthServer {

    @Resource
    private JwtProperties properties;

    public Jws<Claims> parse(String token) {
        String prefix = properties.getPrefix();
        if (StringUtils.isEmpty(token)) {
            log.warn("Token is empty!");
            throw new UnsupportedJwtException("Token is empty!");
        }
        if (!token.startsWith(prefix)) {
            log.warn("Don't have prefix {}!", prefix);
            throw new UnsupportedJwtException("Unsupported jwt token!");
        }
        token = token.substring(properties.getPrefix().length());
        return Jwts.parser().setSigningKey(properties.getSecret()).parseClaimsJws(token);
    }

}
