package com.partner;

import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author king
 * @date 2023/11/25-12:05
 * @Desc
 */
public class ExcelUtils {

    public static CellWriteHandler getHeaderStyle() {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)16);
        headWriteFont.setBold(true);
        headWriteFont.setColor(IndexedColors.WHITE.getIndex());
        headWriteCellStyle.setWriteFont(headWriteFont);

        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)12);
        contentWriteFont.setFontName("宋体");
        // 设置内容垂直居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 设置内容水平居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        CellWriteHandler horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        return horizontalCellStyleStrategy;
    }

    public static <T> List<List<String>> getBeanHeaderList(Class<T> clazz, boolean flag) {
        Field[] fields = FieldUtils.getAllFields(clazz);
        List<List<String>> headList = new ArrayList<>();
        for (Field field : fields) {
            PrmHeadName prmHeadName = field.getAnnotation(PrmHeadName.class);
            List<String> headRow = new ArrayList<>();
            if (prmHeadName != null) {
                String headName = flag ? prmHeadName.cnName() : prmHeadName.enName();
                headRow.add(headName);
            }
            headList.add(headRow);
        }
        return headList;
    }
}
