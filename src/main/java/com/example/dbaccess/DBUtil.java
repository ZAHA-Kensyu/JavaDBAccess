package com.example.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBUtil {
    public static Connection getConnection(){
        try{
            //JDBCドライバの読み込み
            Class.forName("org.postgresql.Driver");
            //接続を返す
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_test","testuser","test");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
