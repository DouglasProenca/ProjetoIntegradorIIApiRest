package com.sistema.apicr7imports.useful;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelEngine {

	public byte[] generateExcel(ArrayList<?> data, String title, String[] titles) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet(title);
		Row row = sheet.createRow(0);
		Cell cellTitle; 
		Cell cellData; 
		String[] dataSplit;
		
		Font fontTitles = workbook.createFont();
		fontTitles.setBold(true);
		
		CellStyle styleTitles = workbook.createCellStyle();
		styleTitles.setAlignment(HorizontalAlignment.CENTER);
		styleTitles.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		styleTitles.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleTitles.setFont(fontTitles);
		
		for (Integer i = 0; i < titles.length; i++) {
			cellTitle = row.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleTitles);

			sheet.setColumnWidth(i, titles[i].length() * 1000);
			
		}

		for (Integer i = 0; i < data.size(); i++) {
			row = sheet.createRow(i + 1);
			dataSplit = data.get(i).toString().split(";");
			
			for (Integer j = 0; j < dataSplit.length; j++) {
				cellData = row.createCell(j);
				cellData.setCellValue(dataSplit[j]);
			}
		}

		workbook.write(byteArrayOutputStream);
		workbook.close();

		return byteArrayOutputStream.toByteArray();
	}
}
