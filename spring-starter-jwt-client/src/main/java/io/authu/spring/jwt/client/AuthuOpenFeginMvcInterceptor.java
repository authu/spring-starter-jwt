package io.authu.spring.jwt.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.authu.spring.jwt.core.request.JwtRequestProperties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/10/15.
 */
public class AuthuOpenFeginMvcInterceptor implements RequestInterceptor {

    @Resource
    private JwtRequestProperties requestProperties;
    @Resource
    private HttpServletRequest mvcRequest;
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String headerName = requestProperties.getHeaderName();
        requestTemplate.header(headerName, mvcRequest.getHeader(headerName));
    }

}
