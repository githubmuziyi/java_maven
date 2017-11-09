package com.muzi.db;

import java.sql.*;

/**
 * Created by muzi on 2017/11/8.
 */
public class DBUtil {

    private static final String URL = "jdbc:mysql://192.168.99.100:32779/imooc";
    private static final String USER = "root";
    private static final String PASSWORD ="root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2.获得数据库链接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM member");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("user_name"));
        }
    }
}
