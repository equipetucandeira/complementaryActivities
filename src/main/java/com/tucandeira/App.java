package com.tucandeira;


import com.tucandeira.domain.*;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.logging.Level;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public final class App extends Application {
  private static Connection connection;

  private static Properties properties;

  private static Student student;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    try { 
      var resourcePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

      var stream = new FileInputStream(resourcePath + "/application.properties");

      properties = new Properties();

      properties.load(stream);

      stream.close();

      Class.forName(properties.getProperty("db.driver"));

      connection = DriverManager.getConnection(
        properties.getProperty("db.url"),
        properties.getProperty("db.user"),
        properties.getProperty("db.password")
      );

      student = new Student(
        UUID.fromString("2f581551-a66a-4fb7-ad88-b702508ee738"),
        "Alice Martins",
        new Email("alice.martins@aluno.ifsp.edu.br"),
        new Password("password")
      );

      stage.setTitle(properties.getProperty("app.window.title"));

      stage.initStyle(StageStyle.UNDECORATED);

      stage.setResizable(false);

      var bounds = Screen.getPrimary().getVisualBounds();

      stage.setX((bounds.getWidth() - stage.getWidth()) / 2);

      stage.setY((bounds.getHeight() - stage.getHeight()) / 2);

      var fxml = "src/main/java/com/tucandeira/ui/menuScreen.fxml";

      Parent root = new FXMLLoader(new File(fxml).toURI().toURL()).load();

      stage.setScene(new Scene(root));

      stage.centerOnScreen();

      stage.show();
    } catch (Exception exception) {
      Logger.getLogger(
        Thread.currentThread().getStackTrace()[0].getClassName()
      ).log(Level.SEVERE, exception.getMessage(), exception);
    }
  }

  @Override
  public void stop() {
    System.exit(0);
  }

  public static Connection getConnection() {
    return connection;
  }

  public static Student getStudent() {
    return student;
  }
}
