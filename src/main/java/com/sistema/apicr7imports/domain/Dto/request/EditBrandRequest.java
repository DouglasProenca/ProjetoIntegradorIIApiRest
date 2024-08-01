package com.sistema.apicr7imports.domain.Dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditBrandRequest {
	
	Integer id;
	String marca;
	Integer country;
	LocalDate data;

}
