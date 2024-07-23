package com.sistema.apicr7imports.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.sistema.apicr7imports.domain.Dto.response.CEPResponse;


@Controller
public class ViaCEPService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${viacep.api.url}")
	private String url;

	public CEPResponse cep(String cep) {
		return restTemplate.getForObject(url.replace("codigoPostal", cep), CEPResponse.class);
	}
}
