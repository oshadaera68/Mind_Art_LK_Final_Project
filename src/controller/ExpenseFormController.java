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
        loadUi("AddExpenseForm");
    }

    public void searchExpenseOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SearchExpenseForm");
    }

    public void deleteExpenseOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteExpenseForm");
    }

    public void selectAllExpenseOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SelectAllExpenseForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void updateExpenseOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("UpdateExpenseForm");
    }
}
