package com.tech.real;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	static Connection conn;
	//static Statement st;
	
   
   
   
   public static Connection getConnection() {
	   System.out.println("数据库连接初始化");
		Connection con = null; // define Connection
		try {
			Class.forName("com.mysql.jdbc.Driver");// load Mysql driver
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "123456");
			if (con!=null) {
				System.out.println("连接成功");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection failed! " + e.getMessage());
		}
		return con;

	}
  




public static Connection getConn() {
	if (conn==null) {
		conn=getConnection();
	}
	return conn;
}




public static void setConn(Connection conn) {
	Dao.conn = conn;
}

}
