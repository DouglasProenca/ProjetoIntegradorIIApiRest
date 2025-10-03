package com.sistema.apicr7imports.controller.impl;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.controller.ICPFController;
import com.sistema.apicr7imports.data.dto.response.CpfResponse;
import com.sistema.apicr7imports.services.ICPFService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/private/cpf/v1")
public class CPFController implements ICPFController {

	private final ICPFService service;

	@GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CpfResponse> verifyCPF(@PathVariable String cpf) {
		return ResponseEntity.ok().body(service.verifyCPF(cpf));
	}
}
