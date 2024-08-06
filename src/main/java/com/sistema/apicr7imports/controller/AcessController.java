package com.sistema.apicr7imports.controller;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.controller.interfaces.AcessControllerInterface;
import com.sistema.apicr7imports.data.dto.request.AcessRequest;
import com.sistema.apicr7imports.data.dto.response.AcessResponse;
import com.sistema.apicr7imports.services.AcessService;

@RestController
@RequestMapping(value = "/acesso")
public class AcessController implements AcessControllerInterface {

	@Autowired
	AcessService service;

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcessResponse> login(@FormParam("username") String username,
			                                   @FormParam("password") String password) {
	  return ResponseEntity.ok().body(service.getAcess(username, password));
	}
	
	@PostMapping(value = "/login/byrequestBody", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcessResponse> login(@RequestBody AcessRequest acessRequest) {
	   return ResponseEntity.ok().body(service.getAcess(acessRequest.getUsername(), acessRequest.getPassword()));
	}
}
