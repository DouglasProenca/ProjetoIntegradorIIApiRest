package com.sistema.apicr7imports.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.apicr7imports.component.CodeString;
import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.services.UserService;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	/*@RequestMapping(value = "/searchuser", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findByUser(@RequestParam(value= "user", defaultValue = "") String user) {
		user = URL.decodeParam(user);
		List<User> list = service.findbyUser(user);
		return ResponseEntity.ok().body(list);
	}*/

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody User obj) {
		obj.setPassword(BCrypt.withDefaults().hashToString(8, obj.getPassword().toCharArray()));
		obj.setMailPassword(CodeString.codeString(obj.getMailPassword()));
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody User obj, @PathVariable Integer id) {
		obj.setId(id);
		obj.setPassword(BCrypt.withDefaults().hashToString(8, obj.getPassword().toCharArray()));
		obj.setMailPassword(CodeString.codeString(obj.getMailPassword()));
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
}