package com.sistema.apicr7imports.services;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
	
	private JavaMailSender getJavaMailSender() throws UnsupportedEncodingException, NullPointerException {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	     JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
         mailSender.setHost("smtp.gmail.com");
         mailSender.setPort(587);
	     mailSender.setUsername(((User) authentication.getPrincipal()).getUserMail());
	     mailSender.setPassword(CodeString.decodeString(((User) authentication.getPrincipal()).getMailPassword()));
	     
	     Properties props = mailSender.getJavaMailProperties();
	     props.put("mail.transport.protocol", "smtp");
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.debug", "false"); // Pode ser false em produção

	     return mailSender;
	 }
	
	 public void sendEmail(MailRequest mailRequest) throws MessagingException, UnsupportedEncodingException, NullPointerException {
        MimeMessage message = getJavaMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        if(!(mailRequest.getAnexoFile() == null || mailRequest.getAnexoFile().equals(""))) {
        	byte[] fileContent = Base64.getDecoder().decode(mailRequest.getAnexoFile());
        	helper.addAttachment(mailRequest.getAnexoTitulo(), new ByteArrayResource(fileContent));
        }
        
        helper.setTo(mailRequest.getDestinatario());
        helper.setSubject(mailRequest.getAssunto());
        helper.setText(mailRequest.getTexto());

        getJavaMailSender().send(message);
    }
	
}
