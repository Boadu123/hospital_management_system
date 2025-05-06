package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hospital";
        String username = "root";
        String password = "Abame123456";

        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully");
            conn.close();
        } catch (SQLException e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}

