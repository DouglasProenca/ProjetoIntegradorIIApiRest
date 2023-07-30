package com.sistema.apicr7imports.resources;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


import com.sistema.apicr7imports.services.JasperService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(value = "/jasper")
public class JasperResource {

	@Autowired
	private JasperService service;

	@GetMapping(value = "/download-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadPDF() throws SQLException, JRException {
		byte[] pdfBytes = service.GerarManagentmentReport();

		// Definir o cabeçalho Content-Disposition para fazer o navegador baixar o arquivo
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("generated.pdf").build());

		return ResponseEntity.ok().headers(headers).body(pdfBytes);
	}
}
