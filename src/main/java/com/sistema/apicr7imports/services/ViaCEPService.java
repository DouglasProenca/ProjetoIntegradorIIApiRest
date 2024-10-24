package com.sistema.apicr7imports.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistema.apicr7imports.data.dto.response.CEPResponse;


@Service
@FeignClient(name = "cep", url = "https://viacep.com.br")
public interface ViaCEPService {

	@GetMapping("/ws/{cep}/json")
	CEPResponse getCEPResponse(@PathVariable("cep") String cep);
}
