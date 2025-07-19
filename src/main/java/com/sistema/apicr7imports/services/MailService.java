package com.sistema.apicr7imports.services;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.util.CodeString;
import com.sistema.apicr7imports.data.dto.request.MailRequest;
import com.sistema.apicr7imports.data.model.User;

@Service
public class MailService {
	
	@Value("${spring.mail.port}")
	Integer port;
	
	@Value("${spring.mail.host}")
	String host;
	
	@Value("${spring.mail.transport.protocol}")
	String protocol;
	
	@Value("${spring.mail.smtp.auth")
	String auth;
	
	@Value("${spring.mail.smtp.starttls.enable}")
	String enabled;
	
	@Value("${spring.mail.debug}")
	String debug;
	
	private JavaMailSender getJavaMailSender() throws UnsupportedEncodingException, NullPointerException {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	     JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
         mailSender.setHost(host);
         mailSender.setPort(port);
	     mailSender.setUsername(((User) authentication.getPrincipal()).getUserMail());
	     mailSender.setPassword(CodeString.decodeString(((User) authentication.getPrincipal()).getMailPassword()));
	     
	     mailSender.getJavaMailProperties().put("mail.transport.protocol", protocol);
	     mailSender.getJavaMailProperties().put("mail.smtp.auth", auth);
	     mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", enabled);
	     mailSender.getJavaMailProperties().put("mail.debug", debug); 

	     return mailSender;
	 }
	
	 public void sendEmail(MailRequest mailRequest) throws MessagingException, UnsupportedEncodingException, NullPointerException {
        MimeMessage message = getJavaMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        if(!(mailRequest.getAttachmentFile() == null || mailRequest.getAttachmentFile().equals(""))) {
        	byte[] fileContent = Base64.getDecoder().decode(mailRequest.getAttachmentFile());
        	helper.addAttachment(mailRequest.getAttachmentTitle(), new ByteArrayResource(fileContent));
        }
        
        helper.setTo(mailRequest.getAdress());
        helper.setSubject(mailRequest.getSubject());
        helper.setText(mailRequest.getText());

        getJavaMailSender().send(message);
    }
	
}
