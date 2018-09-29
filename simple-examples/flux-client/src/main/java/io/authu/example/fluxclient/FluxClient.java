package io.authu.example.fluxclient;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringCloudApplication
public class FluxClient {

	public static void main(String[] args) {
		SpringApplication.run(FluxClient.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "Base Client Started !!";
	}

}
