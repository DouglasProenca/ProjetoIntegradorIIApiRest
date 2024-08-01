package com.sistema.apicr7imports.domain.Dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditProductRequest {

	Integer id;
	String nome;
	Integer brand;
	Double valor;
	Integer quantidade;
	Boolean ativo;
	LocalDate data;
	Integer category;
	
}
