package javaTest;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class Ex02_BCConnetion {
	
	// 1) static, return 값이 있는경우
	
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
	
			Connection cn = DriverManager.getConnection(url,"root","mysql");
			System.out.println("** DB Connection Exception 성공 **");
			return cn;
		} catch (Exception e) {
			System.out.println("** DB Connection Exception => " + e.toString());
			return null;
		} //try,catch
		
	} //getConnection
	
	@Test
	public void connectionTest() {
		System.out.println("** DB Connection => "+getConnection());
		assertNotNull(getConnection());
	}
	
} //class
