package com.partner;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import lombok.Data;

/**
 * @author king
 * @date 2023/11/24-22:41
 * @Desc
 */
@Data
//@HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 10)
// 头字体设置成20
//@HeadFontStyle(fontHeightInPoints = 20)
// 设置全局列宽为20
// 设置全局内容居中
public class User {
    @ExcelProperty
    @PrmHeadName(cnName = "名称", enName = "Name")
    private String name;
    @ExcelProperty
    @PrmHeadName(cnName = "年龄", enName = "Age")
    private Integer age;
    @ExcelProperty
    @PrmHeadName(cnName = "地址", enName = "Address")
    private String address;

    public User(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public static final String datePattern = "((\\d{3}[1-9]|\\d{2}[1-9]\\d|\\d[1-9]\\d{2}|[1-9]\\d{3})-(((0[13578]|1[02])-(0[1-9]|[12]\\d|3[01]))|((0[469]|11)-(0[1-9]|[12]\\d|30))|(02-(0[1-9]|[1]\\d|2[0-8]))))|(((\\d{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-                                                                 29)";

    public static final String mmddyyyy = "";

    public static void main(String[] args) {
        // ((\d{3}[1-9]|\d{2}[1-9]\d|\d[1-9]\d{2}|[1-9]\d{3})-(((0[13578]|1[02])-(0[1-9]|[12]\d|3[01]))|((0[469]|11)-(0[1-9]|[12]\d|30))|(02-(0[1-9]|[1]\d|2[0-8]))))
    }

}
