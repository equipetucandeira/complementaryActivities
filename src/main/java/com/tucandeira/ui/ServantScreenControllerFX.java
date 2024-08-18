package com.tucandeira.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;



public class ServantScreenControllerFX {
    @FXML
    private ListView<String> activitiesListView;
    
  @FXML
    private void goToMainMenu(ActionEvent event) {
      var stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      
      try {
        var loader = new FXMLLoader(new File("src/main/java/com/tucandeira/ui/menuScreen.fxml").toURI().toURL());
        Parent root = loader.load();
            
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
      } catch (IOException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao carregar a tela do menu principal.");
        alert.showAndWait();
      }
  }
  @FXML
    private void approve(ActionEvent event) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Tarefa aprovada");
      alert.setHeaderText(null);
      alert.setContentText("A tarefa foi aprovada com sucesso. As horas já foram contabilizadas!");
      alert.showAndWait();
    }
  @FXML
    private void goToViewDetails(ActionEvent event){
      var stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      
      try {
        var loader = new FXMLLoader(new File("src/main/java/com/tucandeira/ui/detailsScreen.fxml").toURI().toURL());
        Parent root = loader.load();
            
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
      } catch (IOException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao carregar a tela do menu principal.");
        alert.showAndWait();
      }
    }
  @FXML 
  private void listActivities(){
      var activities = FXCollections.observableArrayList(
        "Exemplo 1 - Tipo: Curricular, Data: 01/01/2024",
        "Exemplo 2 - Tipo: Não Curricular, Data: 15/01/2024",
        "Exemplo 3 - Tipo: Curricular, Data: 30/01/2024"
      );
      activitiesListView.setItems(activities);
  }


  @FXML
    public void initialize() {
      listActivities();
    }
    
}

