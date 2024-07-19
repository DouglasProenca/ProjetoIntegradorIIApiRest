package com.sistema.apicr7imports.controller;

import java.sql.SQLException;
import java.text.ParseException;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.services.JasperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.jasperreports.engine.JRException;

@Api(tags = "Relatorios Da Aplicação") 
@RestController
@RequestMapping(value = "/private/jasper")
public class JasperController {

	@Autowired
	private JasperService service;

	@ApiOperation(value = "Relatorio Gerencial")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Relatório criado."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	@GetMapping(value = "/managentmentReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> managentmentPDF() throws SQLException, JRException {
		return service.GerarManagentmentReport();
	}

	@ApiOperation(value = "Relatorio Analitico")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Relatório criado."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	@GetMapping(value = "/analyticalReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> analitycalPDF(@ApiParam(value = "Data Inicial do Periodo.", required = true,example = "2020-01-01")
												@NotNull @RequestParam(value = "dateini") String initial_date,
												@ApiParam(value = "Data final do Periodo.", required = true,example = "2024-01-01")
			                                    @NotNull @RequestParam(value = "datefin") String final_date) throws JRException, SQLException, ParseException {
		return service.gerarAnalyticalReport(initial_date,final_date);
	}
	
	@ApiOperation(value = "Relatorio Sintetico")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Relatório criado."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	@GetMapping(value = "/syntheticReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> syntheticPDF(@ApiParam(value = "Data Inicial do Periodo.", required = true,example = "2020-01-01")
			                                   @NotNull @RequestParam(value = "dateini") String initial_date,
			                                   @ApiParam(value = "Data final do Periodo.", required = true,example = "2024-01-01")
											   @NotNull @RequestParam(value = "datefin") String final_date) throws JRException, SQLException, ParseException {
		return service.gerarSyntheticReport(initial_date,final_date);
	}
}
