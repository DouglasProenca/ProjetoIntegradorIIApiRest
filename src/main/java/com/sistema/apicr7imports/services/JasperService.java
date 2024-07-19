package com.sistema.apicr7imports.services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class JasperService {
	
	@Autowired
	DataSource dataSource;

	public ResponseEntity<byte[]> GerarManagentmentReport() throws SQLException, JRException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		Connection conn = dataSource.getConnection();

		InputStream jasperFile = JasperService.class.getResourceAsStream("/jasper/Rel_Managent_Report_Geral.jasper");
		
		parametros.put("SUBREPORT_DIR", JasperService.class.getResource("/jasper/").toString());
		
		JasperPrint print = JasperFillManager.fillReport(jasperFile, parametros, conn);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("managentment.pdf").build());

		return ResponseEntity.created(null).headers(headers).body(byteArrayOutputStream.toByteArray());
	}

	public ResponseEntity<byte[]>  gerarAnalyticalReport(String initial_date, String final_date) throws JRException, SQLException, ParseException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		Connection conn = dataSource.getConnection();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		InputStream jasperFile = JasperService.class.getResourceAsStream("/jasper/Rel_Analytical_Report_Geral.jasper");

		parametros.put("data1", simpleDateFormat.parse(initial_date));
		parametros.put("data2", simpleDateFormat.parse(final_date));

		JasperPrint print = JasperFillManager.fillReport(jasperFile, parametros, conn);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("analitycal.pdf").build());
		
		return ResponseEntity.created(null).headers(headers).body(byteArrayOutputStream.toByteArray());
	}

	public ResponseEntity<byte[]> gerarSyntheticReport(String initial_date, String final_date) throws JRException, SQLException, ParseException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		Connection conn = dataSource.getConnection();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		InputStream jasperFile = JasperService.class.getResourceAsStream("/jasper/Rel_Synthetic_Report_Geral.jasper");

		parametros.put("data1", simpleDateFormat.parse(initial_date));
		parametros.put("data2", simpleDateFormat.parse(final_date));

		JasperPrint print = JasperFillManager.fillReport(jasperFile, parametros, conn);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("Synthetic.pdf").build());

		return ResponseEntity.created(null).headers(headers).body(byteArrayOutputStream.toByteArray());
	}
}