package com.sistema.apicr7imports.component;

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
public class Excel {

	public ByteArrayOutputStream exportExcel(ArrayList<?> dados, String Titulo, String[] titulos) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		XSSFWorkbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet(Titulo);
		Row row = sheet.createRow(0);
		Font font = workbook.createFont();
		font.setBold(true);
		

		for (int i = 0; i < titulos.length; i++) {

			Cell cell = row.createCell(i);
			cell.setCellValue(titulos[i]);

			sheet.setColumnWidth(i, titulos[i].length() * 1000);

			CellStyle style = workbook.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFont(font);
			
			cell.setCellStyle(style);
		}

		for (int i = 0; i < dados.size(); i++) {
			row = sheet.createRow(i + 1);
			String[] aux = dados.get(i).toString().split(";");
			for (int j = 0; j < aux.length; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(aux[j]);
			}
		}

		workbook.write(baos);
		workbook.close();

		return baos;
	}
}
