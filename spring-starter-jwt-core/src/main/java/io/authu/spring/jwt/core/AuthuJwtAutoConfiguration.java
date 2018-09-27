package io.authu.spring.jwt.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/9/27.
 */
@Configuration
@Import({JwtProperties.class})
@ConditionalOnProperty(prefix = AuthuJwtPrefix.CORE, name = "enabled", havingValue = "true", matchIfMissing = true)
public class AuthuJwtAutoConfiguration {

    @Bean
    public JwtAuthServer jwtAuthServer(){
        return new JwtAuthServer();
    }

}
