package com.sistema.apicr7imports.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.sistema.apicr7imports.domain.CEP;

@Controller
public class ViaCEPService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${viacep.url}")
	private String url;

	public CEP cep(String cep) {
		return restTemplate.getForObject(url.replace("codigoPostal", cep), CEP.class);
	}
}
