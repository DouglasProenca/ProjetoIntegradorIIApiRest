package com.sistema.apicr7imports.controller;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
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
	private JasperService service;

	@GetMapping(value = "/managentmentReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> managentmentPDF() throws SQLException, JRException {
		return service.GerarManagentmentReport();
	}

	@GetMapping(value = "/analyticalReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> analitycalPDF(@RequestParam(value = "dateini") String initial_date,
			                                    @RequestParam(value = "datefin") String final_date) throws JRException, SQLException, ParseException {
		return service.gerarAnalyticalReport(initial_date,final_date);
	}
	
	@GetMapping(value = "/syntheticReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> syntheticPDF(@RequestParam(value = "dateini") String initial_date,
			                                   @RequestParam(value = "datefin") String final_date) throws JRException, SQLException, ParseException {
		return service.gerarSyntheticReport(initial_date,final_date);
	}
}
