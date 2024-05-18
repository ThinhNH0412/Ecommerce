package com.eazydeals.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import jakarta.servlet.http.HttpServlet;

public class ConnectionProvider extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static Connection connection;

	public static Connection getConnection() {

		try {
			if (connection == null) {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=eazydeals;TrustServerCertificate=True;Encrypt=false;user=sa;password=sa");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
        public static void main(String[] args) {
            getConnection();
        }
    

}
