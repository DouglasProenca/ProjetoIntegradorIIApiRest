package com.sistema.apicr7imports.data.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {

	Integer categoryId;
	String categoryName;
	LocalDate date;
	UserDTO user;
	
}
