package com.sistema.apicr7imports.services;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.component.CodeString;
import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.domain.Dto.request.MailRequest;

@Service
public class MailService {
	
	public void sendEmail(MailRequest mailRequest) throws MessagingException, UnsupportedEncodingException, NullPointerException {
		if(mailRequest.getAnexoTitulo() == null) {
			sendSimpleMessage(mailRequest);
		} else {
			sendMessageWithAttachment(mailRequest);
		}
	}
	
	 public JavaMailSender getJavaMailSender() throws UnsupportedEncodingException, NullPointerException {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	     JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
         mailSender.setHost("smtp.gmail.com");
         mailSender.setPort(587);
	     mailSender.setUsername(((User) authentication.getPrincipal()).getMail());
	     mailSender.setPassword(CodeString.decodeString(((User) authentication.getPrincipal()).getMailPassword()));
	     
	     Properties props = mailSender.getJavaMailProperties();
	     props.put("mail.transport.protocol", "smtp");
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.debug", "false"); // Pode ser false em produção

	     return mailSender;
	 }
	

	private void sendSimpleMessage(MailRequest mailRequest) throws UnsupportedEncodingException, NullPointerException {
		JavaMailSender emailSender = getJavaMailSender();
		
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailRequest.getDestinatario());
        message.setSubject(mailRequest.getAssunto());
        message.setText(mailRequest.getTexto());
        
        emailSender.send(message);
    }
	
	private void sendMessageWithAttachment(MailRequest mailRequest) throws MessagingException, UnsupportedEncodingException, NullPointerException {
        MimeMessage message = getJavaMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        byte[] fileContent = Base64.getDecoder().decode(mailRequest.getAnexoFile());

        helper.setTo(mailRequest.getDestinatario());
        helper.setSubject(mailRequest.getAssunto());
        helper.setText(mailRequest.getTexto());
        helper.addAttachment(mailRequest.getAnexoTitulo(), new ByteArrayResource(fileContent));

        getJavaMailSender().send(message);
    }
	
}
