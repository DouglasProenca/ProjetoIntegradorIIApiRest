package com.sistema.apicr7imports.domain.Dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

	Integer id;
	String categoria;
	Date data;
	UserDTO user;
	
}
