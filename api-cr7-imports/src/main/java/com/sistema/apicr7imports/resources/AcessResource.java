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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Autenticação Da Aplicação") 
@RestController
@RequestMapping(value = "/acesso")
public class AcessResource {
	
	@Autowired
	private AcessService service;

	@ApiOperation(value = "Autenticar usuario e retornar um token de acesso")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@FormParam("username") String username, @FormParam("password") String password) {

		Acess acess = service.login(username, password);
		return ResponseEntity.ok().body(acess);
	}

	@ApiOperation(value = "Deslogar Usuario da aplicação")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Object> logout(@RequestHeader("key") String key) {

		service.logout(key);
		return ResponseEntity.ok().build();
	}
}
