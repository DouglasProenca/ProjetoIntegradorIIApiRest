package com.sistema.apicr7imports.data.dto.request;

import lombok.Getter;

@Getter
public class MailRequest {

	String adress;
	String subject;
	String text;
	String attachmentTitle;
	String attachmentFile;
	
}
