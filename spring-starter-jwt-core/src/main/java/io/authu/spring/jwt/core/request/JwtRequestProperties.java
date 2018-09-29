package io.authu.spring.jwt.core.request;

import io.authu.spring.jwt.core.AuthuJwtPrefix;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Request relation config, such as header name etc.
 *
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/9/27 23:15 End.
 */
@Data
@ConfigurationProperties(prefix = AuthuJwtPrefix.REQURST)
public class JwtRequestProperties {

    private boolean filterEnabled = true;
    private String headerName = "Authorization";
    private String headerPrefix = "Bearer ";
    private String attributeName = "token-claims";
    private String excludePatterns;// , split

}
