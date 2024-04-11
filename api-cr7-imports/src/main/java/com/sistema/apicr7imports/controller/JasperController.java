package com.sistema.apicr7imports.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.services.JasperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;

@Api(tags = "Relatorios Da Aplicação") 
@RestController
@RequestMapping(value = "/private/jasper")
public class JasperController {

	@Autowired
	private JasperService service;

	@ApiOperation(value = "Relatorio Gerencial")
	@GetMapping(value = "/managentmentReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadPDF() throws SQLException, JRException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("generated.pdf").build());

		return ResponseEntity.created(null).headers(headers).body(service.GerarManagentmentReport());
	}

	@ApiOperation(value = "Relatorio Analitico")
	@GetMapping(value = "/analyticalReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> analitycalPDF(@NotNull @RequestParam(value = "dateini") String ini_date,
			                                    @NotNull @RequestParam(value = "datefin") String fin_date) throws JRException, SQLException, ParseException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		byte[] pdfBytes = service.gerarAnalyticalReport(simpleDateFormat.parse(ini_date),simpleDateFormat.parse(fin_date));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("analitycal.pdf").build());

		return ResponseEntity.created(null).headers(headers).body(pdfBytes);
	}
	
	@ApiOperation(value = "Relatorio Sintetico")
	@GetMapping(value = "/syntheticReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> syntheticPDF(@NotNull @RequestParam(value = "dateini") String ini_date,
											   @NotNull @RequestParam(value = "datefin") String fin_date) throws JRException, SQLException, ParseException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		byte[] pdfBytes = service.gerarSyntheticReport(simpleDateFormat.parse(ini_date),simpleDateFormat.parse(fin_date));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("Synthetic.pdf").build());

		return ResponseEntity.created(null).headers(headers).body(pdfBytes);
	}
}
