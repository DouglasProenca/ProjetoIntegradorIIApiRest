package com.sistema.apicr7imports.controller.impl;


import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.controller.IMailController;
import com.sistema.apicr7imports.data.dto.request.MailRequest;
import com.sistema.apicr7imports.services.IMailService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/private/mail")
public class MailController implements IMailController {

	private final IMailService mailService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> send(MailRequest mailRequest) throws MessagingException, UnsupportedEncodingException, NullPointerException {
		mailService.sendEmail(mailRequest);
		return ResponseEntity.created(null).build();
	}

}
