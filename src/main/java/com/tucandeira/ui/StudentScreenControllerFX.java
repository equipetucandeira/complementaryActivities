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

public class StudentScreenControllerFX {

    @FXML
    private Button attachmentButton;
    
    @FXML
    private Label attachmentLabel;
    
    @FXML
    private ComboBox<String> comboBox;
    
    @FXML
    private TextField activityNameField;
    
    @FXML
    private TextField workloadField;
    
    @FXML
    private DatePicker startDatePicker;
    
    @FXML
    private DatePicker endDatePicker;
    
    @FXML
    private RadioButton option1;
    
    @FXML
    private RadioButton option2;
    
    @FXML
    private ListView<String> activitiesListView;
    
    
    @FXML
    public void initialize(){
      listTypes();
      listActivities();
    }
    
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
    private void chooseFile(ActionEvent event) {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Selecione um documento");
      fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
      Stage stage = (Stage) attachmentButton.getScene().getWindow();
      var selectedFile = fileChooser.showOpenDialog(stage);
      if (selectedFile != null) {
          attachmentLabel.setText(selectedFile.getName());
      } else {
          attachmentLabel.setText("Nenhum arquivo selecionado");
      }
    }

    @FXML
    private void submitActivity(ActionEvent event) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Tarefa submetida");
      alert.setHeaderText(null);
      alert.setContentText("A tarefa foi enviada com sucesso. Está aguardando avaliação!");
      alert.showAndWait();
    }
    @FXML
    private void listTypes(){
      ObservableList<String> options = FXCollections.observableArrayList(
        "option 1",
        "option 2",
        "option 3"
      );
      comboBox.setItems(options);
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

}
