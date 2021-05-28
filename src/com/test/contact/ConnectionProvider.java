package com.test.contact;

import java.sql.*;


public class ConnectionProvider {
   private static Connection con=null;
	public static Connection getConnection() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			if(con==null)
			{
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb","root","root");
				
				
			}
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	
		 
		return con;
	
	}
	
}
