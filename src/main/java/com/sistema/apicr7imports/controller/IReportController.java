package com.sistema.apicr7imports.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;

@Tag(name = "Relatórios Da Aplicação") 
public interface IReportController {

	@Operation(description = "Relatório Gerencial")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Relatório criado."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
    ResponseEntity<byte[]> managentmentPDF() throws SQLException, JRException;
	
	@Operation(description = "Relatório Analítico")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Relatório criado."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<byte[]> analitycalPDF(@Parameter(description = "Data Inicial do Período.", required = true, example = "2020-01-01")
										 @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initial_date,
										 @Parameter(description = "Data final do Período.", required = true, example = "2024-01-01")
			                             @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate final_date) throws JRException, SQLException, ParseException;
	
	@Operation(description = "Relatório Sintético")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Relatório criado."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
		})
	ResponseEntity<byte[]> syntheticPDF(@Parameter(description = "Data Inicial do Período.", required = true, example = "2020-01-01")
			                            @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initial_date,
			                            @Parameter(description = "Data final do Período.", required = true, example = "2024-01-01")
									    @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate final_date) throws JRException, SQLException, ParseException;
	
}
