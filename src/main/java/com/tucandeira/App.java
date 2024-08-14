package com.tucandeira;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class App {
  private static Connection connection;
  
  private static Properties properties;

  public static void main(String[] args) {
    try {
      boot();
    } catch (Exception exception) {
      exception.printStackTrace(System.out);
    }

    shutdown();
  }

  public static void boot() throws ClassNotFoundException, IOException,SQLException {
    var root = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    var stream = new FileInputStream(root + "/application.properties");

    properties = new Properties();

    properties.load(stream);

    stream.close();

    Class.forName(properties.getProperty("db.driver"));

    connection = DriverManager.getConnection(
      properties.getProperty("db.url"),
      properties.getProperty("db.user"),
      properties.getProperty("db.password")
    );
  }

  public static void shutdown() {
    try {
      connection.close();
    } catch (SQLException exception) {
      System.exit(1);
    }

    System.exit(0);
  }
}
