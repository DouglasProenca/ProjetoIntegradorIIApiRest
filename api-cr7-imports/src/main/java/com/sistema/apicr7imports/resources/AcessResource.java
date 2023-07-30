package com.sistema.apicr7imports.resources;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.domain.Acess;
import com.sistema.apicr7imports.services.AcessService;

@RestController
@RequestMapping(value = "/acesso")
public class AcessResource {
	
	@Autowired
	private AcessService service;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@FormParam("user") String user, @FormParam("password") String password) {

		Acess acess = service.login(user, password);
		return ResponseEntity.ok().body(acess);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Object> logout(@RequestHeader("key") String key) {

		service.logout(key);
		return ResponseEntity.ok().build();
	}
}
