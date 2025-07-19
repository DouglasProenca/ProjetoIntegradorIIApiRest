package com.sistema.apicr7imports.data.dto.request;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ProductRequest {

	String productName;
	Integer brand;
	Double price;
	Integer amount;
	Boolean enabled;
	LocalDate date;
	Integer category;
	
}
