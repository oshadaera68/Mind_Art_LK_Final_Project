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
        URL resource = getClass().getResource("../view/AddItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void searchItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SearchItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void deleteItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void selectAllFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ItemSelectAllForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }
}