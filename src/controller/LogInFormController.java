package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class LogInFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPasswordLog;
    public AnchorPane logInContext;
    public JFXButton btnLogIn;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern userNameRegEx = Pattern.compile("^[a-z]{5}$");
    Pattern passwordRegEx = Pattern.compile("^[0-9]{4}$");

    public void initialize(){
        btnLogIn.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtUserName,userNameRegEx);
        map.put(txtPasswordLog,passwordRegEx);
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        String user = txtUserName.getText();
        String pass = txtPasswordLog.getText();
        if (user.equals("admin") && pass.equals("1234")) {
            URL resource = getClass().getResource("../view/MainForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) logInContext.getScene().getWindow();
            window.setScene(new Scene(load));
            window.setTitle("Timber Mill Management System - v0.1.0");
            window.show();
        } else {
            new Alert(Alert.AlertType.WARNING, "please enter the user name and password", ButtonType.OK).show();
        }
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnLogIn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }
}