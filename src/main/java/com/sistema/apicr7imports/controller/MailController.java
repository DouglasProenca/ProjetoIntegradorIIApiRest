package com.sistema.apicr7imports.controller;


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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "E-Mail")
@RequestMapping(value = "/private/mail")
public class MailController {

	@Autowired
	MailService mailService;
	
	@ApiOperation(value = "Envia um e-mail")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> insert(@RequestBody MailRequest mailRequest) throws MessagingException {
		mailService.sendEmail(mailRequest);
		return ResponseEntity.created(null).build();
	}

}
