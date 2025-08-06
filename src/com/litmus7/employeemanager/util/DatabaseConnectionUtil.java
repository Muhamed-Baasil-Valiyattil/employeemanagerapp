package com.litmus7.employeemanager.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnectionUtil {


    public static Connection getConnection(String dbUrl,String username, String password) throws SQLException{

        try {
            return DriverManager.getConnection(dbUrl,username,password);
        } catch (Exception e) {
            throw new SQLException("Failed to connect to database");
        }
        
    }
    
}
