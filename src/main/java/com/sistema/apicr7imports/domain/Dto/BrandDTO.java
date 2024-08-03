package com.sistema.apicr7imports.domain.Dto;

import java.time.LocalDate;

import com.sistema.apicr7imports.domain.Country;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class BrandDTO {

	@NonNull
	Integer id;
	String marca;
	Country country;
	LocalDate data;
	UserDTO user;
	
}
