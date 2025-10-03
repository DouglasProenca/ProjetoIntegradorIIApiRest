package com.sistema.apicr7imports.controller.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.controller.IReportController;
import com.sistema.apicr7imports.services.ReportService;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/private/jasper")
public class ReportController implements IReportController {

	private final ReportService service;

	@GetMapping(value = "/managentmentReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> managentmentPDF() throws SQLException, JRException {
		return ResponseEntity.created(null).header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("managentment.pdf").build().toString()).body(service.generateManagentmentReport());
	}

	@GetMapping(value = "/analyticalReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> analitycalPDF(@RequestParam LocalDate initial_date, @RequestParam LocalDate final_date) throws JRException, SQLException, ParseException {
		return ResponseEntity.created(null).header(HttpHeaders.CONTENT_DISPOSITION,ContentDisposition.attachment().filename("analitycal.pdf").build().toString()).body(service.generateAnalyticalReport(initial_date,final_date));
	}
	
	@GetMapping(value = "/syntheticReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> syntheticPDF(@RequestParam LocalDate initial_date, @RequestParam LocalDate final_date) throws JRException, SQLException, ParseException {
		return ResponseEntity.created(null).header(HttpHeaders.CONTENT_DISPOSITION,ContentDisposition.attachment().filename("Synthetic.pdf").build().toString()).body(service.generateSyntheticReport(initial_date,final_date));
	}
}
