package com.sistema.apicr7imports.data.dto;

import java.time.LocalDate;

import com.sistema.apicr7imports.data.model.Country;

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
