package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static final String URL = "jdbc:mysql://localhost:3306/diyweb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String ROOT = "diyadmin";
	public static final String PASSWORD = "diyadmin";

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				DemoApplication.URL, DemoApplication.ROOT, DemoApplication.PASSWORD);

		return connection;
	}


}
