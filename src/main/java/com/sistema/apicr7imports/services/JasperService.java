package com.sistema.apicr7imports.services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class JasperService {
	
	@Autowired
	DataSource dataSource;

	public byte[] createManagentmentReport() throws SQLException, JRException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("SUBREPORT_DIR", JasperService.class.getResource("/jasper/").toString());

		InputStream jasperFile = JasperService.class.getResourceAsStream("/jasper/Rel_Managent_Report_Geral.jasper");
		
		JasperPrint print = JasperFillManager.fillReport(jasperFile, parametros, dataSource.getConnection());

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);
		
		return byteArrayOutputStream.toByteArray();
	}

	public byte[] createAnalyticalReport(LocalDate initial_date, LocalDate final_date) throws JRException, SQLException, ParseException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("data1", Date.from(initial_date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		parametros.put("data2", Date.from(final_date.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		InputStream jasperFile = JasperService.class.getResourceAsStream("/jasper/Rel_Analytical_Report_Geral.jasper");

		JasperPrint print = JasperFillManager.fillReport(jasperFile, parametros, dataSource.getConnection());

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);
		
		return byteArrayOutputStream.toByteArray();
	}

	public byte[] createSyntheticReport(LocalDate initial_date, LocalDate final_date) throws JRException, SQLException, ParseException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("data1", Date.from(initial_date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		parametros.put("data2", Date.from(final_date.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		InputStream jasperFile = JasperService.class.getResourceAsStream("/jasper/Rel_Synthetic_Report_Geral.jasper");

		JasperPrint print = JasperFillManager.fillReport(jasperFile, parametros, dataSource.getConnection());

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);

		return byteArrayOutputStream.toByteArray();
	}
}