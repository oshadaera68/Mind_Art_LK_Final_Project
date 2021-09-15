package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LogInFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPasswordLog;
    public AnchorPane logInContext;

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        String user = txtUserName.getText();
        String pass = txtPasswordLog.getText();
        if (user.equals("admin") && pass.equals("1234")) {
            URL resource = getClass().getResource("../view/DashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) logInContext.getScene().getWindow();
            window.setScene(new Scene(load));
            window.setTitle("Timber Mill Management System - v0.1.0");
            window.show();
        } else {
            new Alert(Alert.AlertType.WARNING, "please enter the user name and password", ButtonType.OK).show();
        }
    }
}