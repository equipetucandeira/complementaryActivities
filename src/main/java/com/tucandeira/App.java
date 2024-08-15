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


import com.tucandeira.domain.Email;
import com.tucandeira.domain.Student;
import com.tucandeira.domain.Password;
import com.tucandeira.repository.StudentRepository;

public final class App extends Application{
  private static Connection connection;
  
  private static Properties properties;

  public static void main(String[] args) {
    launch(args);
    //try {
      //boot();
      //var repo = new StudentRepository(connection);
      //repo.save(new Student("Thiago" , new Email("tmribeiro@gmail.com"), new Password("thiago123")));
      

    //} catch (Exception exception) {
    //  exception.printStackTrace(System.out);
    //}

    //shutdown();
    //System.out.println("Hello, Tucandeira!");
  }


  @Override
  public void start(Stage stage) {
    Scene mainMenu = createMainMenuScene(stage);
    Scene addActivity = createSceneStudent(stage);
    Scene evaluation = createSceneEvaluator(stage);
    Scene listActivities = createSceneListActivities(stage);

    stage.setTitle("Menu Principal");
    stage.setScene(mainMenu);
    stage.show();
  }

  private Scene createMainMenuScene(Stage stage) {
    Label label = new Label("Menu Principal");
        
    Button goToSceneStudent = new Button("Adicionar atividade");
    goToSceneStudent.setOnAction(event -> stage.setScene(createSceneStudent(stage)));
        
    Button goToSceneEvaluator = new Button("Avaliar atividade");
    goToSceneEvaluator.setOnAction(event -> stage.setScene(createSceneEvaluator(stage)));

    Button goToSceneList = new Button("Listar atividades");
    goToSceneList.setOnAction(event -> stage.setScene(createSceneListActivities(stage)));
    
    String buttonStyle = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;";
    goToSceneStudent.setStyle(buttonStyle);
    goToSceneEvaluator.setStyle(buttonStyle);
    goToSceneList.setStyle(buttonStyle);
   
    VBox layout = new VBox(15);
    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(20));
    layout.getChildren().addAll(label, goToSceneStudent, goToSceneEvaluator, goToSceneList);
        
    return new Scene(layout, 400, 300);


  }

  private Scene createSceneStudent(Stage stage) {
    Label typeAndPeriodLabel = new Label("Tipo e Período Letivo");

    Label label = new Label("Adicionar atividade");
    Button backToMenu = new Button("Voltar para Menu Principal");
    backToMenu.setOnAction(event -> stage.setScene(createMainMenuScene(stage)));

    Label yearLabel = new Label("Ano Letivo:");
    TextField yearField = new TextField();

    Label periodLabel = new Label("Período Letivo:");
    TextField periodField = new TextField();

    Label vinculationLabel = new Label("Vinculação:");
        
    RadioButton option1 = new RadioButton("Curricular");
    RadioButton option2 = new RadioButton("Não Curricular");
    ToggleGroup group = new ToggleGroup();
    option1.setToggleGroup(group);
    option2.setToggleGroup(group);

    Label activityTypeLabel = new Label("Tipo:");
    ComboBox<String> comboBox = new ComboBox<>();
    comboBox.getItems().addAll("Opção 1", "Opção 2", "Opção 3");

    Label activityNameLabel = new Label("Nome da Atividade:");
    TextField activityNameField = new TextField();

    Label workloadLabel = new Label("Carga Horária:");
    TextField workloadField = new TextField();

    Label startDateLabel = new Label("Data de Início:");
    DatePicker startDatePicker = new DatePicker();

    Label endDateLabel = new Label("Data de Término:");
    DatePicker endDatePicker = new DatePicker();

    Label additionalInfoLabel = new Label("Informações Complementares:");
    TextArea additionalInfoArea = new TextArea();
    additionalInfoArea.setPrefRowCount(4);

    Label attachmentLabel = new Label("Anexo:");
    Button attachmentButton = new Button("Escolher Arquivo");

    Button saveButton = new Button("Salvar");
    Label resultLabel = new Label();

        
    GridPane grid = new GridPane();
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
    Scene scene = new Scene(grid, 500, 400);
    return scene;
  }

  private Scene createSceneEvaluator(Stage stage) {    
    Label titleLabel = new Label("Título da Tarefa:");
    Label taskTitle = new Label("Atividade Exemplo");
        
    Label typeLabel = new Label("Tipo da Tarefa:");
    Label taskType = new Label("Tipo Exemplo");

    Button approveButton = new Button("Aprovar");
    Button rejectButton = new Button("Reprovar");
    Button detailsButton = new Button("Ver Detalhes");

    detailsButton.setOnAction(event -> stage.setScene(createSceneDetails(stage)));

        
    Button backToMenu = new Button("Voltar para Menu Principal");
    backToMenu.setOnAction(event -> stage.setScene(createMainMenuScene(stage)));

        
    GridPane grid = new GridPane();
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

        
    Scene scene = new Scene(grid, 400, 300);
    return scene;
  }

  private Scene createSceneListActivities(Stage stage) {
    Label label = new Label("Lista de atividades");
    ListView<String> listView = new ListView<>();
    ObservableList<String> activities = FXCollections.observableArrayList(
      "Exemplo 1 - Tipo: Curricular, Data: 01/01/2024",
      "Exemplo 2 - Tipo: Não Curricular, Data: 15/01/2024",
      "Exemplo 3 - Tipo: Curricular, Data: 30/01/2024"
    );
    listView.setItems(activities);

    Button backToMenu = new Button("Voltar para Menu Principal");
    backToMenu.setOnAction(event -> stage.setScene(createMainMenuScene(stage)));
        
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);

    grid.setPadding(new Insets(10));
    grid.setAlignment(Pos.CENTER);
    
    grid.add(label, 0, 0, 2, 1); 
    grid.add(listView, 0, 1, 2, 1); 
    grid.add(backToMenu, 0, 2); 

        
    Scene scene = new Scene(grid, 400, 300);
    return scene;
  }

  private Scene createSceneDetails(Stage stage){
    Label titleLabel = new Label("Detalhes da Atividade");

    Label yearLabel = new Label("Ano Letivo:");
    Label yearValue = new Label("2024");

    Label periodLabel = new Label("Período Letivo:");
    Label periodValue = new Label("1");

    Label vinculationLabel = new Label("Vinculação:");
    Label vinculationValue = new Label("Curricular");

    Label typeLabel = new Label("Tipo:");
    Label typeValue = new Label("Tipo selecionado");

    Label additionalInfoLabel = new Label("Informações Complementares:");
    TextArea additionalInfoArea = new TextArea("");
    additionalInfoArea.setEditable(false);

    Button backButton = new Button("Voltar");
    backButton.setOnAction(event -> stage.setScene(createSceneEvaluator(stage)));

    GridPane grid = new GridPane();
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
    
    Scene scene = new Scene(grid, 400, 400);
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
