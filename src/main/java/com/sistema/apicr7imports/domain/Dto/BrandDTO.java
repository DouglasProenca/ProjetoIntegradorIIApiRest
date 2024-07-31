package com.sistema.apicr7imports.domain.Dto;

import java.util.Date;

import com.sistema.apicr7imports.domain.Country;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandDTO {

	Integer id;
	String marca;
	Country country;
	Date data;
	UserDTO user;
	
}
