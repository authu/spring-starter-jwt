package io.authu.spring.jwt.core;

import io.authu.spring.jwt.core.request.JwtRequestProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
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
    @Resource
    private JwtRequestProperties requestProperties;
    @Resource
    private Environment environment;

    public Jws<Claims> parse(String token) {
        String prefix = requestProperties.getHeaderPrefix();

        if (StringUtils.isEmpty(token)) {
            log.warn("Token is empty!");
            throw new UnsupportedJwtException("Token is empty!");
        }
        if (!token.startsWith(prefix)) {
            log.warn("Don't have prefix {}!", prefix);
            throw new UnsupportedJwtException("Unsupported authu jwt token!");
        }

        token = token.substring(prefix.length());
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(token);
        Claims body = claims.getBody();

        //Timeout
        if (body.getIssuedAt() == null || body.getExpiration() == null) {
            log.warn("IssuedAt or Expiration is empty!");
            throw new RequiredTypeException("IssuedAt or Expiration is empty!");
        }
        Duration timeout = Duration.between(body.getIssuedAt().toInstant(), body.getExpiration().toInstant());
        if (timeout.compareTo(properties.getMaxTimeout()) > 0) {
            log.warn("Timeout is bigger than max timeout!");
            throw new ExpiredJwtException(claims.getHeader(), claims.getBody(), "Timeout is bigger than max timeout!");
        }

        //Audience
        String audience = body.getAudience();
        if (StringUtils.isEmpty(audience)){
            log.warn("Audience is empty!");
            throw new RequiredTypeException("Audience is empty!");
        }
        if (!properties.getAudienceAllName().equals(audience)) {
            String applicationName = EnvironmentUtils.getApplicationName(environment);
            if (!audience.equals(applicationName)) {
                log.warn("Audience is incorrect!");
                throw new IncorrectClaimException(claims.getHeader(), claims.getBody(), "Audience is incorrect!");
            }
        }

        return claims;
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
    public String generateUserToken(String subject, String audience) {
        String applicationName = EnvironmentUtils.getApplicationName(environment);
        audience = audience.toLowerCase();
        return generateToken(subject, applicationName, audience, properties.getTimeout());
    }

    /**
     * generate request token
     */
    public String generateRequestToken(String subject, String audience) {
        String applicationName = EnvironmentUtils.getApplicationName(environment);
        audience = audience.toLowerCase();
        String token = generateToken(subject, applicationName, audience, properties.getRequestTimeout());
        return requestProperties.getHeaderPrefix() + token;
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
