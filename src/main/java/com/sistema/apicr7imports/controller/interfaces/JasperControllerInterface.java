package com.sistema.apicr7imports.controller.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.jasperreports.engine.JRException;

@Api(tags = "Relatórios Da Aplicação") 
public interface JasperControllerInterface {

	@ApiOperation(value = "Relatório Gerencial")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Relatório criado."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<byte[]> managentmentPDF() throws SQLException, JRException;
	
	@ApiOperation(value = "Relatório Analítico")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Relatório criado."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<byte[]> analitycalPDF(@ApiParam(value = "Data Inicial do Período.", required = true,example = "2020-01-01")
												@NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initial_date,
												@ApiParam(value = "Data final do Período.", required = true,example = "2024-01-01")
			                                    @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate final_date) 
			                                    		throws JRException, SQLException, ParseException;
	
	@ApiOperation(value = "Relatório Sintético")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Relatório criado."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<byte[]> syntheticPDF(@ApiParam(value = "Data Inicial do Período.", required = true,example = "2020-01-01")
			                                   @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initial_date,
			                                   @ApiParam(value = "Data final do Período.", required = true,example = "2024-01-01")
											   @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate final_date) throws JRException, SQLException, ParseException;
	

}
