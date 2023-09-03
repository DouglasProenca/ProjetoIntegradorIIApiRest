package com.sistema.apicr7imports.resources;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
public class JasperResource {

	@Autowired
	private JasperService service;

	@ApiOperation(value = "Relatorio Gerencial")
	@GetMapping(value = "/managentmentReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadPDF() throws SQLException, JRException {
		byte[] pdfBytes = service.GerarManagentmentReport();

		// Definir o cabeçalho Content-Disposition para fazer o navegador baixar o
		// arquivo
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("generated.pdf").build());

		return ResponseEntity.ok().headers(headers).body(pdfBytes);
	}

	@ApiOperation(value = "Relatorio Analitico")
	@GetMapping(value = "/analyticalReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> analitycalPDF(@RequestParam(value = "dateini", defaultValue = "") String ini_date,
			@RequestParam(value = "datefin", defaultValue = "") String fin_date) throws JRException, SQLException, ParseException {

		byte[] pdfBytes = service.gerarAnalyticalReport(new SimpleDateFormat("yyyy-MM-dd").parse(ini_date),
				new SimpleDateFormat("yyyy-MM-dd").parse(fin_date));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("analitycal.pdf").build());

		return ResponseEntity.ok().headers(headers).body(pdfBytes);
	}
	
	@ApiOperation(value = "Relatorio Sintetico")
	@GetMapping(value = "/syntheticReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> syntheticPDF(@RequestParam(value = "dateini", defaultValue = "") String ini_date,
			@RequestParam(value = "datefin", defaultValue = "") String fin_date) throws JRException, SQLException, ParseException {

		byte[] pdfBytes = service.gerarSyntheticReport(new SimpleDateFormat("yyyy-MM-dd").parse(ini_date),
				new SimpleDateFormat("yyyy-MM-dd").parse(fin_date));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("Synthetic.pdf").build());

		return ResponseEntity.ok().headers(headers).body(pdfBytes);
	}
}
