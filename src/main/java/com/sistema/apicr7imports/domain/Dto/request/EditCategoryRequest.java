package com.sistema.apicr7imports.domain.Dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditCategoryRequest {

	Integer id;
	String categoria;
	LocalDate data;
}
