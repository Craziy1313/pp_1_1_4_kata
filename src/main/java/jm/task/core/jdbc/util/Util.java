package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.*;

public class Util {
    private static final String BD_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String USER = "root";
    private static final String PASSWORD = "Root1234";
    public static Connection getConnection(){
        Connection connection;
        try {
            Class.forName(BD_DRIVER);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

