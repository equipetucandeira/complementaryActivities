/*package com.tucandeira.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public final class JavaFX {
  
  private TabPane tabPane;

  public static Alert alert(String title, String header, String content) {
    var component = new Alert(Alert.AlertType.NONE);

    component.setTitle(title);

    component.setHeaderText(header);

    component.setContentText(content);

    component.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

    return component;
  }
}*/

package com.tucandeira.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

public final class JavaFX {

    @FXML
    private Button myButton;

    @FXML
    private VBox container;

    @FXML
    private void handleButtonClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button Clicked");
        alert.setHeaderText(null);
        alert.setContentText("Button was clicked!");
        alert.showAndWait();
    }
}
