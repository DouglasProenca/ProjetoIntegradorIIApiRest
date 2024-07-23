package com.sistema.apicr7imports.services;

import java.util.Base64;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.domain.Dto.request.MailRequest;


@Service
public class MailService {
	
	@Autowired
    private JavaMailSender emailSender;
	
	public void sendEmail(MailRequest mailRequest) throws MessagingException {
		if(mailRequest.getAnexoTitulo() == null) {
			sendSimpleMessage(mailRequest);
		} else {
			sendMessageWithAttachment(mailRequest);
		}
	}

	private void sendSimpleMessage(MailRequest mailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailRequest.getDestinatario());
        message.setSubject(mailRequest.getAssunto());
        message.setText(mailRequest.getTexto());
        emailSender.send(message);
    }
	
	private void sendMessageWithAttachment(MailRequest mailRequest) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        byte[] fileContent = Base64.getDecoder().decode(mailRequest.getAnexoFile());

        helper.setTo(mailRequest.getDestinatario());
        helper.setSubject(mailRequest.getAssunto());
        helper.setText(mailRequest.getTexto());
        helper.addAttachment(mailRequest.getAnexoTitulo(), new ByteArrayResource(fileContent));

        emailSender.send(message);
    }
	
}
