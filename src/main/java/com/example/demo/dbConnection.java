package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class dbConnection {
    public Connection connectionDb(String dbname,String userName,String pass){
        Connection con = null;
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,userName,pass);
            if(con != null) {
                System.out.println("Connection established");
            } else {
                System.out.println("Connection failed");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
}
