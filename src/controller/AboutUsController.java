package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutUsController {

    public void exitOnAction(ActionEvent actionEvent) throws IOException {
      Stage stage = new Stage();
      stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
      stage.setTitle("Timber Mill Management System - Ver 0.1.0");
      stage.show();
    }
}