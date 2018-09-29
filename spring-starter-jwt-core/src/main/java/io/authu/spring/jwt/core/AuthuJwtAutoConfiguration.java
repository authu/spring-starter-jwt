package io.authu.spring.jwt.core;

import io.authu.spring.jwt.core.request.JwtPathMatcher;
import io.authu.spring.jwt.core.request.JwtRequestProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/9/27.
 */
@Configuration
@Import({JwtProperties.class, JwtRequestProperties.class})
@ConditionalOnProperty(prefix = AuthuJwtPrefix.CORE, name = "enabled", havingValue = "true", matchIfMissing = true)
public class AuthuJwtAutoConfiguration {

    @Resource
    private JwtRequestProperties requestProperties;

    @Bean
    public JwtAuthServer jwtAuthServer(){
        return new JwtAuthServer();
    }

    @PostConstruct
    public void initPathMatcher(){
        String excludePatterns = requestProperties.getExcludePatterns();
        if (!StringUtils.isEmpty(excludePatterns)) {
            JwtPathMatcher.addExcludePatterns(Arrays.asList(excludePatterns.split(",")));
        }
    }

}
