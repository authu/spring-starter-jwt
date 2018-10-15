package io.authu.example.webmvc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/8/2 22:56 End.
 */
@FeignClient(value = "flux-client")
public interface FluxClient {

    @GetMapping("/")
    String getFluxClientIndex();

}
