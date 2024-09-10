package com.sistema.apicr7imports.data.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({ "productId", "productName", "date","enabled","amount", "price","category","brand","user"})
public class ProductDTO {

	Integer productId;
	String productName;
	BrandDTO brand;
	Double price;
	Integer amount;
	Boolean enabled;
	LocalDate date;
	UserDTO user;
	CategoryDTO category;
	
}
