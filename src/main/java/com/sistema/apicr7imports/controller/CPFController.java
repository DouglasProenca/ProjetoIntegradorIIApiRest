package com.sistema.apicr7imports.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.controller.interfaces.CPFControllerInterface;
import com.sistema.apicr7imports.domain.Dto.response.CpfResponse;
import com.sistema.apicr7imports.services.CPFService;

@RestController
@RequestMapping(value = "/private/cpf/v1")
public class CPFController implements CPFControllerInterface {

	@Autowired
	CPFService service;

	@GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CpfResponse> verifyCPF(@PathVariable String cpf) throws SQLException {
		return ResponseEntity.ok().body(service.verifyCPF(cpf));
	}
}
