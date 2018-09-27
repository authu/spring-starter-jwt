package io.authu.spring.jwt.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/9/26.
 */
@Data
@ConfigurationProperties(prefix = AuthuJwtPrefix.CORE)
public class JwtProperties {
    private boolean enabled = true;
    private String header = "Authorization";
    private String prefix = "Bearer ";
    private String secret = "cdsvhsklajxcohbfgflvkmdsochofbnogfmvsdohcurrvdkvfkjdin";
    private Duration timeout = Duration.ofDays(1);
}
