package com.sinoway.common.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConn {

	public static void main(String[] args) {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://127.0.0.1:50000/HDZX";
			String name ="db2admin";
			String pwd = "flyvideo";
			DriverManager.getConnection(url, name, pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
