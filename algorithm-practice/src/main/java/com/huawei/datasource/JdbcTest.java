package com.huawei.datasource;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author king
 * @date 2022/2/16-23:50
 * @Desc
 */
public class JdbcTest {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql:///db1?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT",
                "root", "root");
        System.out.println(conn);
    }
}
