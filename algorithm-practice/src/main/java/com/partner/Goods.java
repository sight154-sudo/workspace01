package com.partner;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;

// 头字体设置成20
//@ColumnWidth(150)
// 设置全局内容居中
//@ContentStyle(verticalAlignment = VerticalAlignmentEnum.CENTER, horizontalAlignment = HorizontalAlignmentEnum.CENTER)
public class Goods{

    // @ExcelProperty作用：设置列标题名称
    @ExcelProperty(index = 0)
    @PrmHeadName(cnName = "名称", enName = "GoodsName")
    private String goodsName;

    @ExcelProperty(index = 1)
    @PrmHeadName(cnName = "类型", enName = "GoodsType")
    private String goodsType;

    @ExcelProperty(index = 2)
    @PrmHeadName(cnName = "生产日期", enName = "GoodsProduceDate")
    private String goodsProduceDate;

    @ExcelProperty(index = 3)
    @PrmHeadName(cnName = "保质期", enName = "GoodsType")
    private String goods2Date;

    @ExcelProperty(index = 4)
    @PrmHeadName(cnName = "核心厂", enName = "GoodsFactoryAddressMain")
    private String goodsFactoryAddressMain;

    @ExcelProperty(index = 5)
    @PrmHeadName(cnName = "副厂", enName = "GoodsFactoryAddressChild")
    private String goodsFactoryAddressChild;

    public Goods() {
    }

    public Goods(String goodsName, String goodsType, String goodsProduceDate, String goods2Date, String goodsFactoryAddressMain, String goodsFactoryAddressChild) {
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.goodsProduceDate = goodsProduceDate;
        this.goods2Date = goods2Date;
        this.goodsFactoryAddressMain = goodsFactoryAddressMain;
        this.goodsFactoryAddressChild = goodsFactoryAddressChild;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsProduceDate() {
        return goodsProduceDate;
    }

    public void setGoodsProduceDate(String goodsProduceDate) {
        this.goodsProduceDate = goodsProduceDate;
    }

    public String getGoods2Date() {
        return goods2Date;
    }

    public void setGoods2Date(String goods2Date) {
        this.goods2Date = goods2Date;
    }

    public String getGoodsFactoryAddressMain() {
        return goodsFactoryAddressMain;
    }

    public void setGoodsFactoryAddressMain(String goodsFactoryAddressMain) {
        this.goodsFactoryAddressMain = goodsFactoryAddressMain;
    }

    public String getGoodsFactoryAddressChild() {
        return goodsFactoryAddressChild;
    }

    public void setGoodsFactoryAddressChild(String goodsFactoryAddressChild) {
        this.goodsFactoryAddressChild = goodsFactoryAddressChild;
    }
}