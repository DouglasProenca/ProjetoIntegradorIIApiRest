package com.sistema.apicr7imports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiCr7ImportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCr7ImportsApplication.class, args);
	}

}
