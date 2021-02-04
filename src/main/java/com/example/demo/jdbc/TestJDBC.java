package com.example.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class TestJDBC {
	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/diyweb?useSSL=false&serverTimezone=UTC";
		String user = "diyadmin";
		String pass = "diyadmin";

		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection successful!");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
