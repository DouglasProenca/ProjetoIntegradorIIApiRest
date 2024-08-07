package com.sistema.apicr7imports.data.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({ "id", "nome", "data","ativo","quantidade", "valor","category","brand","user"})
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
