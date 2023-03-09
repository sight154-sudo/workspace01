package com.chatgpt;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToPdfConverter {
    
    public static void main(String[] args) throws Exception {
        
        // Read Excel data
        Workbook workbook = new XSSFWorkbook("input.xlsx");
        Sheet sheet = workbook.getSheetAt(0);
        Row row;
        Cell cell;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    if (cell != null) {
                        sb.append(cell.toString()).append(" ");
                    }
                }
                sb.append("\n");
            }
        }
        workbook.close();
        
        // Create PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
        document.open();
        Paragraph para = new Paragraph(sb.toString());
        para.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(para);
        document.close();
    }
}
