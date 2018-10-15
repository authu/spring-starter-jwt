package io.authu.example.webmvc;

import feign.RequestInterceptor;
import io.authu.spring.jwt.client.AuthuOpenFeginMvcInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/10/15.
 */
@Configuration
@RestController
@EnableFeignClients
@SpringCloudApplication
public class WebmvcApplication {

    @Resource
    private FluxClient fluxClient;

    public static void main(String[] args) {
        SpringApplication.run(WebmvcApplication.class, args);
    }

    @GetMapping("/")
    public String index(){
        return "Server Webmvc Started !!";
    }

    @GetMapping("/flux")
    public String fluxClient(){
        return fluxClient.getFluxClientIndex();
    }

    @Bean
    public RequestInterceptor feignInterceptor(){
        return new AuthuOpenFeginMvcInterceptor();
    }

}
