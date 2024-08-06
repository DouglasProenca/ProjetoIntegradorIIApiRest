package com.sistema.apicr7imports.data.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class CategoryDTO {

	@NonNull
	Integer id;
	String categoria;
	LocalDate data;
	UserDTO user;
	
}
