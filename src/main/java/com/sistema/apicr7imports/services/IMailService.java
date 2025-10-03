package com.sistema.apicr7imports.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.sistema.apicr7imports.data.dto.request.MailRequest;

public interface IMailService {
	
	 void sendEmail(MailRequest mailRequest) throws MessagingException, UnsupportedEncodingException, NullPointerException;
	
}
