package com.sistema.apicr7imports.data.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

	Integer id;
	String categoria;
	LocalDate data;
	UserDTO user;
	
}
