package com.sistema.apicr7imports.data.dto.request;

import lombok.Getter;

@Getter
public class MailRequest {

	String destinatario;
	String assunto;
	String texto;
	String anexoTitulo;
	String anexoFile;
	
}
