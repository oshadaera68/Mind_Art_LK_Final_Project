package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class LogInFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPasswordLog;
    public AnchorPane logInContext;
    public JFXButton btnLogIn;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern userRegEx = Pattern.compile("^[A-z]{5}$");
    Pattern passRegEx = Pattern.compile("^[0-9]{4}$");

    public void initialize() {
        btnLogIn.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtUserName, userRegEx);
        map.put(txtPasswordLog, passRegEx);
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String username = txtUserName.getText();
        String password = txtPasswordLog.getText();

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("SELECT userName,CAST(AES_DECRYPT(password,?) AS CHAR(50)) AS password FROM user WHERE username = ?");
        pstm.setObject(1, SignUpFormController.SECRET);
        pstm.setObject(2, username);
        System.out.println(pstm.toString());
        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {
            if (password.equals(rst.getString(2))) {
                URL resource = getClass().getResource("../view/MainForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Stage window = (Stage) logInContext.getScene().getWindow();
                window.setScene(new Scene(load));
                window.setTitle("Timber Mill Management System - v0.1.0");
                window.show();
            } else {
                //invalid password
                new Alert(Alert.AlertType.WARNING, "Invalid Password").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid Username").show();
        }
    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SignUpForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) logInContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Sign Up");
        window.show();
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