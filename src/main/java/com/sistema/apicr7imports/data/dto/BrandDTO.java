package com.sistema.apicr7imports.data.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sistema.apicr7imports.data.model.Country;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({ "id", "marca", "data", "country", "user" })
public class BrandDTO {

	Integer id;
	String marca;
	Country country;
	LocalDate data;
	UserDTO user;
	
}
