/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.connect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class MyConnection {
    private String host;
    private String user;
    private String pass;
    private String url;
    Connection conn;

    public MyConnection() {
        host = "localhost";
        user = "root";
        pass = "Your_pw";//>>>>>>>>>Mật khẩu SQL WorkBench của bạn<<<<<<<<<<
        url = "jdbc:mysql://localhost:3306/luyentapjava?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    }

    public MyConnection(String host, String user, String pass, String url) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.url = url;
    }

    public Connection getConnection() {
        try {
            if(conn != null){
                return conn;
            }
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }   
}
