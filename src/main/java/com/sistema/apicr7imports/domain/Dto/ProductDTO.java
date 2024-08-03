package com.sistema.apicr7imports.domain.Dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

	Integer id;
	String nome;
	BrandDTO brand;
	Double valor;
	Integer quantidade;
	Boolean ativo;
	LocalDate data;
	UserDTO user;
	CategoryDTO category;
	
}
