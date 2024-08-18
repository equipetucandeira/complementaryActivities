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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;



public class ServantScreenControllerFX {
    @FXML
    private ListView<String> activitiesListView;

    @FXML
    private TreeView<String> myTreeView;
    
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
    private void goToViewDetails(){
      var stage = (Stage) myTreeView.getScene().getWindow();
      try {
        var loader = new FXMLLoader(new File("src/main/java/com/tucandeira/ui/detailsScreen.fxml").toURI().toURL());
        Parent root = loader.load();

        var scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
      } catch (IOException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao carregar a tela de detalhes.");
        alert.showAndWait();
      }    
    }
  @FXML
  private void listTreeActivities(){
    var rootItem = new TreeItem<String>("Atividades submetidas");
    rootItem.setExpanded(true);
    
    var needsToAnalyze = new TreeItem<String>("Atividades a serem analisadas");
    var item1 = new TreeItem<String>("Atividade 1");
    var item2 = new TreeItem<String>("Atividade 2");
    var item3 = new TreeItem<String>("Atividade 3");

    needsToAnalyze.getChildren().addAll(item1, item2, item3);

    var expired = new TreeItem<String>("Atividades expiradas");
    var itemE1 = new TreeItem<String>("Atividade 1");
    var itemE2 = new TreeItem<String>("Atividade 2");
    var itemE3 = new TreeItem<String>("Atividade 3");

    expired.getChildren().addAll(itemE1, itemE2, itemE3);

    var approved = new TreeItem<String>("Atividades aprovadas");
    var itemA1 = new TreeItem<String>("Atividade 1");
    var itemA2 = new TreeItem<String>("Atividade 2");
    var itemA3 = new TreeItem<String>("Atividade 3");

    approved.getChildren().addAll(itemA1, itemA2, itemA3);

    var rejected = new TreeItem<String>("Atividades reprovadas");
    var itemR1 = new TreeItem<String>("Atividade 1");
    var itemR2 = new TreeItem<String>("Atividade 2");
    var itemR3 = new TreeItem<String>("Atividade 3");

    rejected.getChildren().addAll(item1, item2, item3);

    rootItem.getChildren().addAll(needsToAnalyze, expired, approved, rejected);

    myTreeView.setRoot(rootItem);

    myTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null && newValue.isLeaf()) {
            goToViewDetails();
        }
    });
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
      listTreeActivities();
    }
    
}

