package com.tucandeira.ui;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class MenuControllerFX {
  
  @FXML
  private void goToStudentScreen(ActionEvent event) throws Exception {
      Parent root = FXMLLoader.load(new File("src/main/java/com/tucandeira/ui/studentScreen.fxml").toURI().toURL());
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(root));
  }
  
  @FXML
  private void goToServantScreen(ActionEvent event) throws Exception {
      Parent root = FXMLLoader.load(new File("src/main/java/com/tucandeira/ui/servantScreen.fxml").toURI().toURL());
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(root));
  }
  
  @FXML
  private void closeScreen(ActionEvent event) throws Exception {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
      

  }
    
    
}
