package com.partner;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author king
 * @date 2023/11/24-22:57
 * @Desc
 */
public class Main {

    public List<List<String>> getUserHeader(boolean flag) {
        List<List<String>> list = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        List<String> head1 = new ArrayList<>();
        List<String> head2 = new ArrayList<>();
        if (flag) {
            head0.add("姓名");
            head1.add("年龄");
            head2.add("地址");
        } else {
            head0.add("name");
            head1.add("age");
            head2.add("address");
        }
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    @Test
    public void test01() {
        File file = new File("C:\\Users\\King\\Downloads\\01.xlsx");
        // 构造产品数据

//        WriteTable writeTable = new WriteTable();
        CellWriteHandler headerStyle = ExcelUtils.getHeaderStyle();

        ExcelWriter excelWriter = null;
        WriteSheet writeSheet = null;
        List<List<String>> userHead = ExcelUtils.getBeanHeaderList(User.class, false);
        List<List<String>> departmentHead = ExcelUtils.getBeanHeaderList(Department.class, false);
        List<List<String>> goodsHead = ExcelUtils.getBeanHeaderList(Goods.class, false);
        excelWriter = EasyExcel.write(file).build();
        try {
            for (int i = 0; i < 200000; i++) {
                List<User> userList = getUserList(i + 1);
                List<Department> deptList = getDepartmentList(i);
                List<Goods> goodsList = getGoodsList(i);
                List<SheetInfoBean> sheetInfoList = new LinkedList<>();
                sheetInfoList.add(new SheetInfoBean("用户信息", userHead, userList));
                sheetInfoList.add(new SheetInfoBean("部门信息", departmentHead, deptList));
                sheetInfoList.add(new SheetInfoBean("产品信息", goodsHead, goodsList));

                for (SheetInfoBean bean : sheetInfoList) {
                    // 构建sheet对象
                    writeSheet = EasyExcel.writerSheet(bean.getSheetName())
                            .registerWriteHandler(headerStyle)
                            .registerWriteHandler(new CustomCellWriteWidthConfig())
                            .head(bean.getHead()).build();
                    // 写出sheet数据
                    excelWriter.write(bean.getDataList(), writeSheet);
                }
            }
            // 关流
        } finally {
            if (excelWriter != null) excelWriter.finish();
        }
    }


    private List<Goods> getGoodsList(int i) {
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods("小面包" + i, "速食", "2023-07-21", "3天", "成都", "上海"));
        goodsList.add(new Goods("旺旺仙贝" + i, "膨化食品", "2023-07-21", "3天", "仙贝中心", "山寨仙贝厂"));
        goodsList.add(new Goods("领克03" + i, "汽车", "2023-07-21", "永久", "领克工厂", "领克副厂"));
        return goodsList;
    }

    public List<User> getUserList(int i) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("小红" + i, 18, "北京" + i));
        userList.add(new User("小白" + i, 17, "上海" + i));
        userList.add(new User("小蓝" + i, 18, "深圳" + i));
        return userList;
    }

    public List<Department> getDepartmentList(int i) {
        List<Department> deptList = new ArrayList<>();
        deptList.add(new Department("java开发部" + i, "DEV001", "南京", "总部"));
        deptList.add(new Department("测试部" + i, "TEST001", "上海", "研发中心"));
        deptList.add(new Department("财务" + i, "ECONOMY001", "南京", "总部"));
        return deptList;
    }
}
