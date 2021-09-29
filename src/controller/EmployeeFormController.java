package controller;

import javafx.event.ActionEvent;
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
        loadUi("AddEmployeeForm");
    }

    public void searchEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SearchEmployeeForm");
    }

    public void deleteEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteEmployeeForm");
    }

    public void selectAllEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("EmployeeSelectAllForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void employeePaymentFormOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("EmployeePaymentForm");
    }

    public void updateEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("UpdateEmployeeForm");
    }
}