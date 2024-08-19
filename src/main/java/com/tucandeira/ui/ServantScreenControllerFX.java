package com.tucandeira.ui;

import com.tucandeira.App;
import com.tucandeira.domain.Activity;
import com.tucandeira.repository.ActivityRepository;

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
  private void goToViewDetails(Activity activity){
    var stage = (Stage) myTreeView.getScene().getWindow();
    
    try {
      var loader = new FXMLLoader(new File("src/main/java/com/tucandeira/ui/detailsScreen.fxml").toURI().toURL());

      loader.getNamespace().put("id", activity.getUUID().toString());

      loader.getNamespace().put("name", activity.getName());

      loader.getNamespace().put("link", activity.isCurriculumLinked()? "Sim" : "Não");

      loader.getNamespace().put("category", activity.getCategory().getName());
      loader.getNamespace().put("categoryid", activity.getCategory().getID().toString());

      loader.getNamespace().put("workload", activity.getWorkload());

      loader.getNamespace().put("start", activity.getStart());

      loader.getNamespace().put("end", activity.getEnd());

      loader.getNamespace().put("attached", activity.getAttached());

      Parent root = loader.load();

      stage.setScene(new Scene(root));

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
    
    var needsToAnalyze = new TreeItem<String>("Atividades em espera");
    var expired = new TreeItem<String>("Atividades expiradas");
    var approved = new TreeItem<String>("Atividades aprovadas");
    var reproved = new TreeItem<String>("Atividades reprovadas");

    var activities = new ActivityRepository(App.getConnection()).listAll();

    for (var activity : activities) {
      var item = new TreeItem<String>(activity.getName());

      System.out.println(activity.getName() + " - " + activity.getStatus() + " Approved: " + activity.isApproved());

        if (activity.getStatus().equals("WAITING")) {
          needsToAnalyze.getChildren().add(item);
        } else if (activity.getStatus().equals("EXPIRED")) {
          expired.getChildren().add(item);
        } else if (activity.getStatus().equals("ANALYZED")) {
          if (activity.isApproved()) {
            approved.getChildren().add(item);
          } else {
            reproved.getChildren().add(item);
          }
         }
      }

    rootItem.getChildren().addAll(needsToAnalyze, expired, approved, reproved);

    myTreeView.setRoot(rootItem);

    myTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null && newValue.isLeaf()) {
            goToViewDetails((Activity)activities.toArray()[0]); // TODO: trocar pela atividade clicada
        }
    });
  }

  @FXML
    public void initialize() {
      listTreeActivities();
    }
}
