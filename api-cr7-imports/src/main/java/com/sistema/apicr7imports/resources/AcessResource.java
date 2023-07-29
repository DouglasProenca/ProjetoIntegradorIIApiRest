package com.sistema.apicr7imports.resources;

import java.io.Serializable;
import java.security.GeneralSecurityException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.domain.Acess;
import com.sistema.apicr7imports.domain.Autenticator;
import com.sistema.apicr7imports.domain.Headers;

@RestController
@RequestMapping(value = "/acesso")
public class AcessResource {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@FormParam("user") String user, @FormParam("password") String password) {

		Autenticator autenticator = new Autenticator();
		Acess acess = autenticator.login(user, password);
		return ResponseEntity.ok().body(acess);
	}

	@GET
	@Path("get")
	public Response get() {
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("message", "Get executed!");
		JsonObject jsonObj = jsonObjBuilder.build();

		return Response.ok(jsonObj.toString()).build();
	}

	@POST
	@Path("logout")
	public Response logout(@Context HttpHeaders httpHeaders) {
		try {
			Autenticator demoAuthenticator = Autenticator.getInstance();
			String serviceKey = httpHeaders.getHeaderString(Headers.KEY);
			String authToken = httpHeaders.getHeaderString(Headers.TOKEN);
			demoAuthenticator.logout(serviceKey, authToken);
			return Response.ok().build();
		} catch (GeneralSecurityException ex) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
