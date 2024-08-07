package com.sistema.apicr7imports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ThirdAPIConfig {

	@Bean
	RestTemplate template() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add((request, body, execution) -> {;
            return execution.execute(request, body);
        });
		return restTemplate;
	}
}
