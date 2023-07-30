package com.sistema.apicr7imports.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class JasperService {

	@Autowired
	private DatabaseService repo;

	public byte[] GerarManagentmentReport() throws SQLException, JRException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		Connection conn = repo.getDatabaseConnection();

		Class<JasperService> clazz = JasperService.class;
		InputStream jasperFile = clazz.getResourceAsStream("jasper/Rel_Managent_Report_Geral.jasper");
		
		parametros.put("SUBREPORT_DIR",JasperService.class.getResource("jasper/").toString());

		JasperPrint print = JasperFillManager.fillReport(jasperFile, parametros, conn);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);

		return byteArrayOutputStream.toByteArray();
	}

}
