package com.partner;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;

//@HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 10)
// 头字体设置成20
//@HeadFontStyle(fontHeightInPoints = 20)
//@ColumnWidth(150)
// 设置全局内容居中
//@ContentStyle(verticalAlignment = VerticalAlignmentEnum.CENTER, horizontalAlignment = HorizontalAlignmentEnum.CENTER)
public class Department {

    // @ExcelProperty作用：设置列标题名称
    @ExcelProperty(index = 0)
    @PrmHeadName(cnName = "部门名称", enName = "DeptName")
    private String deptName;

    @ExcelProperty(index = 1)
    @PrmHeadName(cnName = "部门Code", enName = "DeptCode")
    private String deptCode;

    @ExcelProperty(index = 2)
    @PrmHeadName(cnName = "部门地址", enName = "DeptLocation")
    private String deptLocation;

    @ExcelProperty(index = 3)
    @PrmHeadName(cnName = "部门类型", enName = "DeptType")
    private String deptType;

    public Department() {
    }

    public Department(String deptName, String deptCode, String deptLocation, String deptType) {
        this.deptName = deptName;
        this.deptCode = deptCode;
        this.deptLocation = deptLocation;
        this.deptType = deptType;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptLocation() {
        return deptLocation;
    }

    public void setDeptLocation(String deptLocation) {
        this.deptLocation = deptLocation;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }
}