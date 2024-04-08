package com.sistema.apicr7imports.services.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Douglas
 */
public class Excel {

    private WritableCellFormat cellFormat;

    private WritableFont getFontbol(boolean bold) {
        return new WritableFont(WritableFont.ARIAL, 10, bold ? WritableFont.BOLD : WritableFont.NO_BOLD);
    }

    private WritableCellFormat getCellFormat(boolean bold, boolean color) throws WriteException {
        cellFormat = new WritableCellFormat(getFontbol(bold));
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setVerticalAlignment(VerticalAlignment.JUSTIFY);
        cellFormat.setBackground(color ? Colour.AQUA : Colour.WHITE);
        return cellFormat;
    }

    public ByteArrayOutputStream exportExcel(ArrayList<?> dados, String Titulo, String[] titulos) throws WriteException, IOException {
            //Instaciando a classe que gera o novo arquivo do excel
    	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableWorkbook workbook = Workbook.createWorkbook(baos);

            //criando uma nova planilha
            WritableSheet sheet = workbook.createSheet(Titulo, 0);

            for (int i = 0; i < titulos.length; i++) {
                Label labelTitulo = new Label(i, 0, titulos[i]);
                sheet.addCell(labelTitulo);
                sheet.setColumnView(i, titulos[i].length() * 3);
                labelTitulo.setCellFormat(getCellFormat(true, true));
            }
            //Para os Dados
            for (int i = 0; i < dados.size(); i++) {
                String[] aux = dados.get(i).toString().split(";");
                for (int j = 0; j < aux.length; j++) {
                    Label label1 = new Label(j, i+1, aux[j]);
                    sheet.addCell(label1);
                }
            }
         
            workbook.write();
            workbook.close();
            
            return baos;
    }

    public String[][] importExcel(File arquivo, JTable table) {
        String colunas[][] = null;
        try {
            Workbook workbook = Workbook.getWorkbook(arquivo);
            Sheet sheet = workbook.getSheet(0);
            colunas = new String[sheet.getRows()][table.getColumnCount()];

            for (int z = 1; z < sheet.getRows(); z++) {
                for (int i = 0; i < table.getColumnCount(); i++) {
                    colunas[z][i] = sheet.getCell(i, z).getContents();
                }
            }

            workbook.close();
        } catch (IOException | BiffException ex) {
        	ex.printStackTrace();
        }
        return colunas;
    }
    
}
