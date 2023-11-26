package com.partner;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;

/**
 * @description: 表格标题处理
 * @author: dmw
 * @date: 2023/3/2 17:00
 **/
public class CustomTitleWriteHandler implements SheetWriteHandler {

    /**
     * 标题
     */
    private final String fileName;

    /**
     * DTO数据类型
     */
    private final Class<?> elementType;

    public CustomTitleWriteHandler(Class<?> elementType,String fileName) {
        this.fileName = fileName;
        this.elementType = elementType;
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        // 获取clazz所有的属性
        Field[] fields = this.elementType.getDeclaredFields();
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        Row row1 = sheet.createRow(0);
        row1.setHeight((short) 800);
        Cell cell = row1.createCell(0);
        //设置标题
        cell.setCellValue(fileName);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) 400);
        font.setFontName("宋体");
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
//        sheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, fields.length-1));
    }
}

