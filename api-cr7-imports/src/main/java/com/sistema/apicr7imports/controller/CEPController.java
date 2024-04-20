package com.sistema.apicr7imports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.apicr7imports.domain.CEP;
import com.sistema.apicr7imports.services.ViaCEPService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Controller
@Api(tags = "CEP")
@RequestMapping(value = "/private/cep")
public class CEPController {

	@Autowired
	ViaCEPService viaCEPService;
	
	@GetMapping(value = "/{cep}", produces = "application/json")
	public ResponseEntity<CEP> findById(@ApiParam(value = "CEP do Endereço.", required = true) @PathVariable String cep) {
		return ResponseEntity.ok().body(viaCEPService.cep(cep));
	}
	
}
