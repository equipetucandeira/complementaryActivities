package com.tucandeira.ui;

import com.tucandeira.App;
import com.tucandeira.repository.*;
import com.tucandeira.domain.*;

import java.nio.file.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private CheckBox curriculumLink;
    
    @FXML
    private ListView<String> categoriesListView;

    @FXML
    private TreeView<String> myTreeView;
    
    private File file;

    private Collection<Category> categories = new ArrayList<>();

    private HashMap<String, Category> nameToCategory = new HashMap<>();

    @FXML
    public void initialize(){
      this.categories = new CategoryRepository(App.getConnection()).list();
      for (var c : this.categories) {
        nameToCategory.put(c.getName(), c);
      }
      this.comboBox.setPromptText("Selecione uma categoria");
      this.comboBox.setItems(FXCollections.observableArrayList(this.categories.stream().map(category -> category.getName()).collect(Collectors.toList())));
      this.categoriesListView.setItems(FXCollections.observableArrayList(this.categories.stream().map(category -> category.toString()).collect(Collectors.toList())));
      listTreeActivities();
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
          this.file = selectedFile;
      } else {
          attachmentLabel.setText("Nenhum arquivo selecionado");
      }
    }

    @FXML
    private void submitActivity(ActionEvent event) {  
      if (activityNameField.getText().isEmpty() || workloadField.getText().isEmpty() || this.file == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso!");
        alert.setHeaderText(null);
        alert.setContentText("Preencha corretamente todos os campos!");
        alert.showAndWait();
        return;
      }

      try {

      var name = activityNameField.getText();

      var workload = Integer.valueOf(workloadField.getText());

      var start = startDatePicker.getValue();

      var end = endDatePicker.getValue();

      var link = curriculumLink.isSelected();

      var category = comboBox.getValue();

      var targetDir = new File("./src/main/resources/static");
      
      if (!targetDir.exists()) {
        targetDir.mkdirs();
      }

      var targetPath = targetDir.toPath().resolve(UUID.randomUUID().toString());

      Files.copy(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

      var activity = new Activity(UUID.randomUUID(), name, workload, start, end, link, targetPath.toString(), nameToCategory.get(category));

      activity.setStudent(App.getStudent());

      new ActivityRepository(App.getConnection()).save(activity);
    } catch (Exception exception) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Ops!");
      alert.setHeaderText(null);
      alert.setContentText(exception.getMessage());
      alert.showAndWait(); 
      return;
      }

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Tarefa submetida");
      alert.setHeaderText(null);
      alert.setContentText("A tarefa foi enviada com sucesso. Aguarde a avaliação!");
      alert.showAndWait();
    }

    @FXML
    private void listTreeActivities(){
    var rootItem = new TreeItem<String>("Atividades submetidas");
      rootItem.setExpanded(true);
    
    var needsToAnalyze = new TreeItem<String>("Atividades em espera");
    var expired = new TreeItem<String>("Atividades expiradas");
    var approved = new TreeItem<String>("Atividades aprovadas");
    var reproved = new TreeItem<String>("Atividades reprovadas");

    var activities = new ActivityRepository(App.getConnection()).list();

    for (var activity : activities) {
      var item = new TreeItem<String>(activity.getName());

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
  }
}
