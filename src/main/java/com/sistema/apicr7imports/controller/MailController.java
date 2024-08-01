package com.sistema.apicr7imports.controller;


import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.domain.Dto.request.MailRequest;
import com.sistema.apicr7imports.services.MailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "E-Mail")
@RequestMapping(value = "/private/mail")
public class MailController {

	@Autowired
	MailService mailService;
	
	@Operation(description = "Envia um e-mail")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> insert(@RequestBody MailRequest mailRequest) throws MessagingException, UnsupportedEncodingException, NullPointerException {
		mailService.sendEmail(mailRequest);
		return ResponseEntity.created(null).build();
	}

}
