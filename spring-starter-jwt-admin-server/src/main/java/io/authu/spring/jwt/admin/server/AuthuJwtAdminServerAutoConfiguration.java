package io.authu.spring.jwt.admin.server;

import de.codecentric.boot.admin.server.domain.values.InstanceId;
import de.codecentric.boot.admin.server.ui.config.AdminServerUiAutoConfiguration;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import de.codecentric.boot.admin.server.web.client.InstanceExchangeFilterFunction;
import io.authu.spring.jwt.core.JwtAuthServer;
import io.authu.spring.jwt.core.JwtProperties;
import io.authu.spring.jwt.core.JwtRequestProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.annotation.Resource;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/9/27.
 */
@Slf4j
@Configuration
@ConditionalOnClass({AdminServerUiAutoConfiguration.class})
public class AuthuJwtAdminServerAutoConfiguration {

    @Resource
    private JwtProperties properties;
    @Resource
    private JwtAuthServer authServer;

    @Bean
    public InstanceExchangeFilterFunction adminAudit() {
        return (instance, request, next) -> {
            InstanceId instanceId = instance.getId();
            String name = instance.getRegistration().getName();
            log.debug("name {}, {} for {} on {}", name, request.method(), instanceId, request.url());
            return next.exchange(request);
        };
    }

    @Bean
    public HttpHeadersProvider jwtHeadersProvider() {
        return (instance) -> {
            HttpHeaders headers = new HttpHeaders();
            JwtRequestProperties request = properties.getRequest();
            headers.set(
                    request.getHeaderName(),
                    authServer.generateRequestToken("admin-server", "admin-server", instance.getRegistration().getName())
            );
            return headers;
        };
    }

}
