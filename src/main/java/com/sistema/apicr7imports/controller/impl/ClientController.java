package com.sistema.apicr7imports.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.apicr7imports.data.dto.request.ClientRequest;
import com.sistema.apicr7imports.data.dto.response.ClientDTO;
import com.sistema.apicr7imports.services.ClientService;

@RestController
@RequestMapping(value = "/private/client")
public class ClientController {
	
	@Autowired
	ClientService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTO> save(@RequestBody ClientRequest clientRequest) throws Exception {
		ClientDTO clientCreate = service.save(clientRequest);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(clientCreate.getClientId()).toUri()).body(clientCreate);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
