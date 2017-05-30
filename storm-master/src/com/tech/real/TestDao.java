package com.tech.real;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class TestDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      TestDao.insert("北京市");
	}
	static Connection conn;
	static Statement st;
   static PreparedStatement pre;

	
	public static void insert(String area) {
		conn = Dao.getConn(); // get connection
		
		System.out.println("开始插入数据");
		try {
			String sql="update position set num=num+1 where area=?;";
			pre=(PreparedStatement) conn.prepareStatement(sql);
			pre.setString(1, area);
			System.out.println(sql);
			int result=pre.executeUpdate(); // exec sql 
			System.out.println("数据更新结果："+result);
			conn.commit();
		} catch (SQLException e) {
			System.out.println("update failed! " + e.getMessage());
		}
		
	}

}
