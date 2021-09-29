package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ItemFormController {
    public AnchorPane context;

    public void addItemOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddItemForm");
    }

    public void searchItemOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SearchItemForm");
    }

    public void deleteItemOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteItemForm");
    }

    public void selectAllFormOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ItemSelectAllForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void updateItemOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("UpdateItemForm");
    }
}