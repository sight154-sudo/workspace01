package com.huawei.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * @author king
 * @date 2022/2/16-23:50
 * @Desc
 */
public class JdbcTest {
    public static void main01(String[] args) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql:///db1?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT",
                "root", "root");
        System.out.println(conn);
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = JDBCUtils.getConn();
        conn.setAutoCommit(false);
        String sql = "insert into t values(?,?,?,?,?)";
        PreparedStatement prep = conn.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            prep.setInt(1, i+2);
            prep.setString(2, "wuhan");
            prep.setString(3, "zhangsan"+i);
            prep.setInt(4, new Random().nextInt(99)+1);
            prep.setString(5, "bb");
            prep.addBatch();
        }
        prep.executeBatch();
        //手动提交事务
        conn.commit();
        long endTime = System.currentTimeMillis();
        JDBCUtils.release(conn, prep);
    }

}
