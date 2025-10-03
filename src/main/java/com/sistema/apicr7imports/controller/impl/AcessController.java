package com.sistema.apicr7imports.controller.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.controller.IAcessController;
import com.sistema.apicr7imports.data.dto.request.AcessRequest;
import com.sistema.apicr7imports.data.dto.response.AcessResponse;
import com.sistema.apicr7imports.services.IAcessService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/acesso")
public class AcessController implements IAcessController {

	private final IAcessService service;

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcessResponse> login(@NotNull @FormParam("username") String username, @NotNull @FormParam("password") String password) {
	  return ResponseEntity.ok().body(service.getAcess(username, password));
	}
	
	@PostMapping(value = "/login/byrequestBody", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcessResponse> login(@RequestBody @Valid AcessRequest acessRequest) {
	   return ResponseEntity.ok().body(service.getAcess(acessRequest.getUsername(), acessRequest.getPassword()));
	}
}
