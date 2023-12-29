package com.sistema.apicr7imports.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.exception.InvalidJwtAuthenticationException;
import com.sistema.apicr7imports.repository.UserRepository;
import com.sistema.apicr7imports.security.jwt.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Autenticação Da Aplicação")
@RestController
@RequestMapping(value = "/acesso")
public class AcessResource {

	@Autowired
	UserRepository repository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	@ApiOperation(value = "Autenticar usuario e retornar um token de acesso")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@FormParam("username") String username,@FormParam("password") String password) {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			User user = repository.findByUsername(username);

			String token = "";

			if (user != null) {
				token = tokenProvider.createToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + " not found!");
			}

			Map<String, String> model = new HashMap<String,String>();
			model.put("username", username);
			model.put("token", token);
			return ResponseEntity.ok().body(model);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw new InvalidJwtAuthenticationException("Usuário ou senha Inválidos!");
		}
	}
}
