package com.sistema.apicr7imports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.controller.interfaces.CEPControllerInterface;
import com.sistema.apicr7imports.domain.Dto.response.CEPResponse;
import com.sistema.apicr7imports.services.ViaCEPService;


@RestController
@RequestMapping(value = "/private/cep")
public class CEPController implements CEPControllerInterface {

	@Autowired
	ViaCEPService viaCEPService;
	
	@GetMapping(value = "/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CEPResponse> findById(@PathVariable String cep) {
		return ResponseEntity.ok().body(viaCEPService.getCEPResponse(cep));
	}
	
}
