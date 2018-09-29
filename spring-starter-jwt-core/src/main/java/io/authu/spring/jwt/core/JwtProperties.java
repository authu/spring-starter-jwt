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

    /**
     * Open/stop authu jwt config, default true.
     */
    private boolean enabled = true;

    /**
     * Secret
     * todo done it by other way
     */
    private String secret = "cdsvhsklajxcohbfgflvkmdsochofbnogfmvsdohcurrvdkvfkjdin";

    /**
     * Generate token to set expire time
     * user?
     */
    private Duration timeout = Duration.ofDays(1);

    /**
     * Token max expire time, from create time
     */
    private Duration maxTimeout = Duration.ofDays(10);

    /**
     * for each request in cloud, should generate a new token, with this timeout
     */
    private Duration requestTimeout = Duration.ofMinutes(10);

    /**
     * re generate
     */
    private boolean reGenerateEnabled = true;

    /**
     * Request relation config, such as header name etc.
     */
    private JwtRequestProperties request = new JwtRequestProperties();
}
