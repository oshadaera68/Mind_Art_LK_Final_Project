package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class EmployeeFormController {
    public AnchorPane context;

    public void addEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddEmployeeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void searchEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SearchEmployeeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void deleteEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteEmployeeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void selectAllEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/EmployeeSelectAllForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void employeePaymentFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/EmployeePaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) context.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Employee Payment");
        window.show();
    }
}