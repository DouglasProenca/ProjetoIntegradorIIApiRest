package com.sistema.apicr7imports.domain.Dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailRequest {

	String destinatario;
	String assunto;
	String texto;
	String anexoTitulo;
	String anexoFile;
	
}
