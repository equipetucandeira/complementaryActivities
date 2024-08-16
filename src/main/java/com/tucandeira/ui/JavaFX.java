package com.tucandeira.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public final class JavaFX {
  public static Alert alert(String title, String header, String content) {
    var component = new Alert(Alert.AlertType.NONE);

    component.setTitle(title);

    component.setHeaderText(header);

    component.setContentText(content);

    component.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

    return component;
  }
}
