package com.sistema.apicr7imports.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.sistema.apicr7imports.data.dto.request.MailRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "E-Mail")
public interface IMailController {

	@Operation(description = "Enviar um e-mail")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "E-Mail enviado."),
			@ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
			@ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.") 
	     })
	ResponseEntity<Void> send(@RequestBody MailRequest mailRequest) throws MessagingException, UnsupportedEncodingException, NullPointerException;

}
