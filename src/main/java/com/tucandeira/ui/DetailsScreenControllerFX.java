package com.tucandeira.ui;

import com.tucandeira.App;
import com.tucandeira.domain.Activity;
import com.tucandeira.repository.*;

import java.nio.file.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;

public class DetailsScreenControllerFX {
  @FXML
  private Button attachmentDownload;

  @FXML
  private Label labelName;
  
  @FXML
  private Label labelCategoryID;
  
  @FXML
  private Label labelID;
  
  @FXML
  private Label labelLink;

  @FXML
  private Label labelCategory;

  @FXML
  private Label labelWorkload;

  @FXML
  private Label labelStart;
  
  @FXML
  private Label labelEnd;
  
  @FXML
  private Label labelAttached;
  
  @FXML
  private TextArea textCommentary;

  @FXML
  private void download(ActionEvent event) {
    try {
      var directoryChooser = new DirectoryChooser();

      directoryChooser.setTitle("Selecione o destino do arquivo");

      var stage = (Stage)attachmentDownload.getScene().getWindow();

      var directory = directoryChooser.showDialog(stage);

      if (directory == null) {
        return;
      }

      var source = new File(labelAttached.getText());

      var target = new File(directory, source.getName() + ".pdf");

      Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);

      var alert = new Alert(Alert.AlertType.INFORMATION, "Download realizado em " + target.getAbsolutePath());

      alert.showAndWait();
    } catch (Exception exception) {
      exception.printStackTrace();

      var alert = new Alert(Alert.AlertType.ERROR, "Não foi possível fazer o download no momento. Tente novamente mais tarde!");
      
      alert.showAndWait();
    }
  }

  @FXML
  private void goToServantScreen(ActionEvent event) {
    var stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      
    try {
      var loader = new FXMLLoader(new File("src/main/java/com/tucandeira/ui/servantScreen.fxml").toURI().toURL());
      
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
    try {
    var activity = new Activity();

    activity.setUUID(labelID.getText());

    activity.setName(labelName.getText());

    activity.setCategory(new CategoryRepository(App.getConnection()).find(UUID.fromString(labelCategoryID.getText())).get());

    activity.setCurriculumLink((labelLink.getText() == "Sim"));

    activity.setWorkload(Integer.valueOf(labelWorkload.getText()));

    activity.setStart(LocalDate.parse(labelStart.getText()));

    activity.setEnd(LocalDate.parse(labelEnd.getText()));

    activity.setAttached(labelAttached.getText());
    activity.setApproved(true);

    new ActivityRepository(App.getConnection()).update(activity);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Tarefa aprovada");
    alert.setHeaderText(null);
    alert.setContentText("A tarefa foi aprovada com sucesso. As horas já foram contabilizadas!");
    alert.showAndWait();
    goToServantScreen(event);

    } catch (Exception exception) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Ops!");
    alert.setHeaderText(null);
    alert.setContentText("Não foi possível avaliar a tarefa. Tente novamente mais tarde!");
    alert.showAndWait();
    System.out.println(exception.getMessage());
    exception.printStackTrace();
    goToServantScreen(event);
    }
  }

  @FXML
  private void reprove(ActionEvent event) {
    try {
    var activity = new Activity();

    activity.setUUID(labelID.getText());

    activity.setName(labelName.getText());

    activity.setCategory(new CategoryRepository(App.getConnection()).find(UUID.fromString(labelCategoryID.getText())).get());

    activity.setCurriculumLink((labelLink.getText() == "Sim"));

    activity.setWorkload(Integer.valueOf(labelWorkload.getText()));

    activity.setStart(LocalDate.parse(labelStart.getText()));

    activity.setEnd(LocalDate.parse(labelEnd.getText()));

    activity.setAttached(labelAttached.getText());

    activity.setCommentary(textCommentary.getText());

    activity.setApproved(false);

    new ActivityRepository(App.getConnection()).update(activity);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Tarefa reprovada");
    alert.setHeaderText(null);
    alert.setContentText("O estudante será notificado!");
    alert.showAndWait();
    goToServantScreen(event);

    } catch (Exception exception) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Ops!");
    alert.setHeaderText(null);
    alert.setContentText("Não foi possível avaliar a tarefa. Tente novamente mais tarde!");
    alert.showAndWait();
    exception.printStackTrace();
    System.out.println(exception.getMessage());
    goToServantScreen(event);
    }
  }

  @FXML
  public void initialize() {
  }
}
