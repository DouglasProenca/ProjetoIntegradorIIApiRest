package com.sistema.apicr7imports.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.controller.interfaces.JasperControllerInterface;
import com.sistema.apicr7imports.services.JasperService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(value = "/private/jasper")
public class JasperController implements JasperControllerInterface {

	@Autowired
	JasperService service;

	@GetMapping(value = "/managentmentReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> managentmentPDF() throws SQLException, JRException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("managentment.pdf").build());
		return ResponseEntity.created(null).headers(headers).body(service.createManagentmentReport());
	}

	@GetMapping(value = "/analyticalReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> analitycalPDF(@RequestParam(value = "dateini") LocalDate initial_date,
			                                    @RequestParam(value = "datefin") LocalDate final_date) throws JRException, SQLException, ParseException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("analitycal.pdf").build());
		return ResponseEntity.created(null).headers(headers).body(service.createAnalyticalReport(initial_date,final_date));
	}
	
	@GetMapping(value = "/syntheticReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> syntheticPDF(@RequestParam(value = "dateini") LocalDate initial_date,
			                                   @RequestParam(value = "datefin") LocalDate final_date) throws JRException, SQLException, ParseException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("Synthetic.pdf").build());
		return ResponseEntity.created(null).headers(headers).body(service.createSyntheticReport(initial_date,final_date));
	}
}
