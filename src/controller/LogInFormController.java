package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInFormController {
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        String user = txtUserName.getText();
        String pass = txtPassword.getText();
        if (user.equals("admin") && pass.equals("1234")){
           Stage stage = new Stage();
           stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
           stage.setTitle("Timber Mill Management System - Ver 0.1.0");
           stage.show();
        }else{
            new Alert(Alert.AlertType.WARNING, "please enter the user name and password", ButtonType.OK).show();
        }
    }
}
