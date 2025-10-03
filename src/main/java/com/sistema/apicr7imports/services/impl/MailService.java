package com.sistema.apicr7imports.services.impl;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.config.MailConfigProperties;
import com.sistema.apicr7imports.data.dto.request.MailRequest;
import com.sistema.apicr7imports.data.model.User;
import com.sistema.apicr7imports.services.IMailService;
import com.sistema.apicr7imports.useful.CodeStringHelper;

@Service
@RequiredArgsConstructor
public class MailService implements IMailService {
	
    private final MailConfigProperties mailConfigProperties;
	
	private JavaMailSender getJavaMailSender() throws UnsupportedEncodingException, NullPointerException {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	     JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
         mailSender.setHost(mailConfigProperties.getHost());
         mailSender.setPort(mailConfigProperties.getPort());
	     mailSender.setUsername(((User) authentication.getPrincipal()).getUserMail());
	     mailSender.setPassword(CodeStringHelper.decodeString(((User) authentication.getPrincipal()).getMailPassword()));
	     
	     mailSender.getJavaMailProperties().put("mail.transport.protocol", mailConfigProperties.getTransport().getProtocol());
	     mailSender.getJavaMailProperties().put("mail.smtp.auth", true);
	     mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", true);
	     mailSender.getJavaMailProperties().put("mail.debug", false); 

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
