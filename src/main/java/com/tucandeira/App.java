package com.tucandeira;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Properties;

public final class App extends Application{
  private static Connection connection;
  
  private static Properties properties;

  public static void main(String[] args) {
    try {
      boot();

      launch(args);
    } catch (Exception exception) {
      exception.printStackTrace(System.out);
    }

    shutdown();
  }

  @Override
  public void start(Stage stage) {
    var mainMenu = createMainMenuScene(stage);
    var addActivity = createSceneStudent(stage);
    var evaluation = createSceneEvaluator(stage);
    var listActivities = createSceneListActivities(stage);

    stage.setTitle(properties.getProperty("app.window.title"));
    stage.setScene(mainMenu);
    stage.show();
  }

  private Scene createMainMenuScene(Stage stage) {
    var label = new Label("Menu Principal");
        
    var goToSceneStudent = new Button("Adicionar atividade");
    goToSceneStudent.setOnAction(event -> stage.setScene(createSceneStudent(stage)));
        
    var goToSceneEvaluator = new Button("Avaliar atividade");
    goToSceneEvaluator.setOnAction(event -> stage.setScene(createSceneEvaluator(stage)));

    var goToSceneList = new Button("Listar atividades");
    goToSceneList.setOnAction(event -> stage.setScene(createSceneListActivities(stage)));
    
    var buttonStyle = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;";
    goToSceneStudent.setStyle(buttonStyle);
    goToSceneEvaluator.setStyle(buttonStyle);
    goToSceneList.setStyle(buttonStyle);
   
    var layout = new VBox(15);
    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(20));
    layout.getChildren().addAll(label, goToSceneStudent, goToSceneEvaluator, goToSceneList);
        
    return new Scene(layout, 400, 300);
  }

  private Scene createSceneStudent(Stage stage) {
    var typeAndPeriodLabel = new Label("Tipo e Período Letivo");

    var label = new Label("Adicionar atividade");
    var backToMenu = new Button("Voltar para Menu Principal");
    backToMenu.setOnAction(event -> stage.setScene(createMainMenuScene(stage)));

    var yearLabel = new Label("Ano Letivo:");
    var yearField = new TextField();

    var periodLabel = new Label("Período Letivo:");
    var periodField = new TextField();

    var vinculationLabel = new Label("Vinculação:");
        
    var option1 = new RadioButton("Curricular");
    var option2 = new RadioButton("Não Curricular");
    var group = new ToggleGroup();
    option1.setToggleGroup(group);
    option2.setToggleGroup(group);

    var activityTypeLabel = new Label("Tipo:");
    var comboBox = new ComboBox<String>();
    comboBox.getItems().addAll("Opção 1", "Opção 2", "Opção 3");

    var activityNameLabel = new Label("Nome da Atividade:");
    var activityNameField = new TextField();

    var workloadLabel = new Label("Carga Horária:");
    var workloadField = new TextField();

    var startDateLabel = new Label("Data de Início:");
    var startDatePicker = new DatePicker();

    var endDateLabel = new Label("Data de Término:");
    var endDatePicker = new DatePicker();

    var additionalInfoLabel = new Label("Informações Complementares:");
    var additionalInfoArea = new TextArea();
    additionalInfoArea.setPrefRowCount(4);

    var attachmentLabel = new Label("Anexo:");
    var attachmentButton = new Button("Escolher Arquivo");

    var saveButton = new Button("Salvar");
    var resultLabel = new Label();
        
    var grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(10));
    grid.setAlignment(Pos.CENTER);
        
    grid.add(yearLabel, 0, 0);
    grid.add(yearField, 1, 0);
    grid.add(periodLabel, 0, 1);
    grid.add(periodField, 1, 1);

    grid.add(vinculationLabel, 0, 2);
    grid.add(option1, 1, 2);
    grid.add(option2, 2, 2);

    grid.add(activityTypeLabel, 0, 3);
    grid.add(comboBox, 1, 3);

    grid.add(activityNameLabel, 0, 4);
    grid.add(activityNameField, 1, 4);

    grid.add(workloadLabel, 0, 5);
    grid.add(workloadField, 1, 5);

    grid.add(startDateLabel, 0, 6);
    grid.add(startDatePicker, 1, 6);

    grid.add(endDateLabel, 0, 7);
    grid.add(endDatePicker, 1, 7);

    grid.add(additionalInfoLabel, 0, 8);
    grid.add(additionalInfoArea, 1, 8, 2, 1);

    grid.add(attachmentLabel, 0, 9);
    grid.add(attachmentButton, 1, 9);

    grid.add(saveButton, 1, 10);
    grid.add(resultLabel, 1, 11);

    grid.add(backToMenu, 0, 12, 2, 1);

    // Criar e retornar a cena
    var scene = new Scene(grid, 500, 400);
    return scene;
  }

  private Scene createSceneEvaluator(Stage stage) {    
    var titleLabel = new Label("Título da Tarefa:");
    var taskTitle = new Label("Atividade Exemplo");
        
    var typeLabel = new Label("Tipo da Tarefa:");
    var taskType = new Label("Tipo Exemplo");

    var approveButton = new Button("Aprovar");
    var rejectButton = new Button("Reprovar");
    var detailsButton = new Button("Ver Detalhes");

    detailsButton.setOnAction(event -> stage.setScene(createSceneDetails(stage)));
        
    var backToMenu = new Button("Voltar para Menu Principal");
    backToMenu.setOnAction(event -> stage.setScene(createMainMenuScene(stage)));

    var grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(10));
    grid.setAlignment(Pos.CENTER);
        
    grid.add(titleLabel, 0, 0);
    grid.add(taskTitle, 1, 0);
    grid.add(typeLabel, 0, 1);
    grid.add(taskType, 1, 1);

    grid.add(approveButton, 0, 2);
    grid.add(rejectButton, 1, 2);
    grid.add(detailsButton, 2, 2);

    grid.add(backToMenu, 0, 3, 3, 1);

    var scene = new Scene(grid, 400, 300);
    return scene;
  }

  private Scene createSceneListActivities(Stage stage) {
    var label = new Label("Lista de atividades");
    var listView = new ListView<String>();
    var activities = FXCollections.observableArrayList(
      "Exemplo 1 - Tipo: Curricular, Data: 01/01/2024",
      "Exemplo 2 - Tipo: Não Curricular, Data: 15/01/2024",
      "Exemplo 3 - Tipo: Curricular, Data: 30/01/2024"
    );
    listView.setItems(activities);

    var backToMenu = new Button("Voltar para Menu Principal");
    backToMenu.setOnAction(event -> stage.setScene(createMainMenuScene(stage)));
        
    var grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);

    grid.setPadding(new Insets(10));
    grid.setAlignment(Pos.CENTER);
    
    grid.add(label, 0, 0, 2, 1); 
    grid.add(listView, 0, 1, 2, 1); 
    grid.add(backToMenu, 0, 2); 
        
    var scene = new Scene(grid, 400, 300);
    return scene;
  }

  private Scene createSceneDetails(Stage stage){
    var titleLabel = new Label("Detalhes da Atividade");

    var yearLabel = new Label("Ano Letivo:");
    var yearValue = new Label("2024");

    var periodLabel = new Label("Período Letivo:");
    var periodValue = new Label("1");

    var vinculationLabel = new Label("Vinculação:");
    var vinculationValue = new Label("Curricular");

    var typeLabel = new Label("Tipo:");
    var typeValue = new Label("Tipo selecionado");

    var additionalInfoLabel = new Label("Informações Complementares:");
    var additionalInfoArea = new TextArea("");
    additionalInfoArea.setEditable(false);

    var backButton = new Button("Voltar");
    backButton.setOnAction(event -> stage.setScene(createSceneEvaluator(stage)));

    var grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(10));
    grid.setAlignment(Pos.CENTER);

    grid.add(titleLabel, 0, 0, 2, 1);

    grid.add(yearLabel, 0, 1);
    grid.add(yearValue, 1, 1);
    grid.add(periodLabel, 0, 2);
    grid.add(periodValue, 1, 2);

    grid.add(vinculationLabel, 0, 3);
    grid.add(vinculationValue, 1, 3);

    grid.add(typeLabel, 0, 4);
    grid.add(typeValue, 1, 4);

    grid.add(additionalInfoLabel, 0, 5);
    grid.add(additionalInfoArea, 0, 6, 2, 1);

    grid.add(backButton, 0, 7, 2, 1);
    
    var scene = new Scene(grid, 400, 400);
    return scene;
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
