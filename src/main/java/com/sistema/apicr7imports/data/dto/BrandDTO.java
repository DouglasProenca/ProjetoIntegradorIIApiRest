package com.sistema.apicr7imports.data.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sistema.apicr7imports.data.model.Country;

import lombok.Data;

@Data
@JsonPropertyOrder({ "brandId", "brandName", "date", "country", "user" })
public class BrandDTO {

	Integer brandId;
	String brandName;
	Country country;
	LocalDate date;
	UserDTO user;
	
}
