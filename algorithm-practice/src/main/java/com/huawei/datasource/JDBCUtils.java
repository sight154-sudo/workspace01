package com.huawei.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author king
 * @date 2022/11/3-23:12
 * @Desc
 */
public class JDBCUtils {

    private static String url;
    private static String user;
    private static String password;
    private static Connection conn;


    static {
        try {
            Properties properties = new Properties();
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(in);
            Class.forName(properties.getProperty("driverClassName"));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static Connection getConn() {
        return conn;
    }

    public static void release(Connection conn, PreparedStatement prep) {
        try {
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void release(Connection conn, PreparedStatement prep, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
