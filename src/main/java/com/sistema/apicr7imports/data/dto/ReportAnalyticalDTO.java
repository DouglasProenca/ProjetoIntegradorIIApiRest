package com.sistema.apicr7imports.data.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportAnalyticalDTO {

	String cliente;
	Integer id_venda;
	Date data;
	BigDecimal valor;
	String produto;
	Integer quantidade;
	String categoria;
	
}
