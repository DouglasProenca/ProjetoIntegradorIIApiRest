package com.sistema.apicr7imports.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.apicr7imports.component.CodeString;
import com.sistema.apicr7imports.data.dto.request.CreateUserRequest;
import com.sistema.apicr7imports.data.model.User;
import com.sistema.apicr7imports.services.UserService;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping(value = "/private/users")
public class UserController {

	@Autowired
	UserService service;

	@GetMapping(value = "/blocked", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> save(@RequestBody CreateUserRequest createUserRequest) {
		User user = service.insert(createUserRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody User obj, @PathVariable Integer id) {
		obj.setUserId(id);
		obj.setPassword(BCrypt.withDefaults().hashToString(8, obj.getPassword().toCharArray()));
		obj.setMailPassword(CodeString.codeString(obj.getMailPassword()));
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/searchuser", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findByUser(@RequestParam(value= "user", defaultValue = "teste") String user) {
		//user = URL.decodeParam(user);
		//List<User> list = service.findbyUser(user);
		return ResponseEntity.ok().body(null);
	}
}