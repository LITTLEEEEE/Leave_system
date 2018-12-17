package com.example.demo.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBManager {
    private static final String url = "jdbc:mysql://66.98.124.133:3306/webhomework?useSSL=true&characterEncoding=utf-8";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "123456lys";
    public Connection connection = null;
    public PreparedStatement preparedStatement = null;

    public DBManager(String sql) throws Exception {
        Class.forName(name);
        connection = DriverManager.getConnection(url, username, password);
        preparedStatement = connection.prepareStatement(sql);
    }

    public void close() {
        try {
            this.connection.close();
            this.preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
