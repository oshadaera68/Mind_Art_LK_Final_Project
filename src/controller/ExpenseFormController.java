package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ExpenseFormController {
    public AnchorPane context;

    public void addExpenseOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddExpenseForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void searchExpenseOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SearchExpenseForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void deleteExpenseOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteExpenseForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void selectAllExpenseOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SelectAllExpenseForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }
}
