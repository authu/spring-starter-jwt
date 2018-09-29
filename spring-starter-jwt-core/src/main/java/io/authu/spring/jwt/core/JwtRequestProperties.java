package io.authu.spring.jwt.core;

import lombok.Data;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/9/27 23:15 End.
 */
@Data
public class JwtRequestProperties {

    private boolean filterEnabled = true;
    private String headerName = "Authorization";
    private String headerPrefix = "Bearer ";
    private String attributeName = "token-claims";

}
