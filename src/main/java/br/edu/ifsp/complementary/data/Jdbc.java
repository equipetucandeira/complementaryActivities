package br.edu.ifsp.complementary.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Jdbc {
	private Connection connection;

	public Jdbc(String url, String user, String password) throws SQLException {
		this.connection = DriverManager.getConnection(url, user, password);
	}

	public Connection getConnection() {
		return this.connection;
	}
}
