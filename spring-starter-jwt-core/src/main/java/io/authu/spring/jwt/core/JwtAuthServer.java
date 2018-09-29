package io.authu.spring.jwt.core;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/9/26.
 */
@Slf4j
public class JwtAuthServer {

    private SecretKey key;
    @Resource
    private JwtProperties properties;

    public Jws<Claims> parse(String token) {
        String prefix = properties.getRequest().getHeaderPrefix();
        if (StringUtils.isEmpty(token)) {
            log.warn("Token is empty!");
            throw new UnsupportedJwtException("Token is empty!");
        }
        if (!token.startsWith(prefix)) {
            log.warn("Don't have prefix {}!", prefix);
            throw new UnsupportedJwtException("Unsupported jwt token!");
        }
        token = token.substring(prefix.length());
        return Jwts.parser().setSigningKey(properties.getSecret()).parseClaimsJws(token);
    }

    /**
     * generate token
     */
    public String generateToken(String subject, String issuer, String audience, Duration timeout) {
        return Jwts.builder()
                .signWith(getKey())
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setAudience(audience)
                .setExpiration(Date.from(Instant.now().plusSeconds(timeout.getSeconds())))
                .compact();
    }

    /**
     * generate user token
     */
    public String generateUserToken(String subject, String issuer, String audience) {
        audience = audience.toLowerCase();
        return generateToken(subject, issuer, audience, properties.getTimeout());
    }

    /**
     * generate request token
     */
    public String generateRequestToken(String subject, String issuer, String audience) {
        audience = audience.toLowerCase();
        String token = generateToken(subject, issuer, audience, properties.getRequestTimeout());
        return properties.getRequest().getHeaderPrefix() + token;
    }

    /**
     * re generate request token
     * todo
     */
    /*public String reGenerateRequestToken(String subject, String issuer, String audience, Duration timeout) {
        return Jwts.builder()
                .signWith(getKey())
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setAudience(audience)
                .setExpiration(Date.from(Instant.now().plusSeconds(timeout.getSeconds())))
                .compact();
    }*/

    private SecretKey getKey(){
        if (key != null) {
            return key;
        }
        String secret = properties.getSecret();
        if (StringUtils.isEmpty(secret)) {
            key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            return key;
        }
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return key;
    }

}
