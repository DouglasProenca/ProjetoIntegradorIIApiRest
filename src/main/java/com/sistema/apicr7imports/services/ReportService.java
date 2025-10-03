package com.sistema.apicr7imports.services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.data.dto.ReportAnalyticalDTO;
import com.sistema.apicr7imports.data.dto.ReportSyntheticDTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

@Service
public class ReportService {
	
	@Autowired
	DataSource dataSource;

	public byte[] generateManagentmentReport() throws SQLException, JRException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("SUBREPORT_DIR", ReportService.class.getResource("/jasper/").toString());

		InputStream jasperFile = ReportService.class.getResourceAsStream("/jasper/Rel_Managent_Report_Geral.jasper");
		
		JasperPrint print = JasperFillManager.fillReport(jasperFile, parametros, dataSource.getConnection());

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);
		
		return byteArrayOutputStream.toByteArray();
	}

	public byte[] generateAnalyticalReport(LocalDate initial_date, LocalDate final_date) throws JRException, SQLException, ParseException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("data1", Date.from(initial_date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		parametros.put("data2", Date.from(final_date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		List<ReportAnalyticalDTO> list = new ArrayList<ReportAnalyticalDTO>();
		list.add(new ReportAnalyticalDTO("Douglas",1,new Date(),BigDecimal.ONE,"Produto 1",1,"Categoria 1"));
		list.add(new ReportAnalyticalDTO("Teste 2",2,new Date(),BigDecimal.ONE,"Produto 2",2,"Categoria 2"));
		
		InputStream jasperFile = ReportService.class.getResourceAsStream("/jasper/Rel_Analytical_Report_Geral.jasper");

		JasperPrint print = JasperFillManager.fillReport(jasperFile, parametros, new JRBeanArrayDataSource(list.toArray()));

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);
		
		return byteArrayOutputStream.toByteArray();
	}

	public byte[] generateSyntheticReport(LocalDate initial_date, LocalDate final_date) throws JRException, SQLException, ParseException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("data1", Date.from(initial_date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		parametros.put("data2", Date.from(final_date.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		List<ReportSyntheticDTO> list = new ArrayList<ReportSyntheticDTO>();
		list.add(new ReportSyntheticDTO("Douglas",1,new Date(),BigDecimal.ONE));
		
		InputStream jasperFile = ReportService.class.getResourceAsStream("/jasper/Rel_Synthetic_Report_Geral.jasper");

		JasperPrint print = JasperFillManager.fillReport(jasperFile, parametros, new JRBeanArrayDataSource(list.toArray()));

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		JasperExportManager.exportReportToPdfStream(print, byteArrayOutputStream);

		return byteArrayOutputStream.toByteArray();
	}
}