package com.huawei.poi;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * @author king
 * @date 2024/3/4-23:54
 * @Desc
 */
public class ExcelDemo {

    @Test
    public void test01() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
    }
}
