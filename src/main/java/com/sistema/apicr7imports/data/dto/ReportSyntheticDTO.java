package com.sistema.apicr7imports.data.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportSyntheticDTO {

	String nome;
	Integer id;
	Date data;
	BigDecimal total;
	
}
