package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class WoodTypeFormController {
    public AnchorPane context;

    public void addWoodTypeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddWoodTypeForm");
    }

    public void searchWoodTypeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SearchWoodTypeForm");
    }

    public void deleteWoodTypeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteWoodTypeForm");
    }

    public void selectAllWoodTypeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SelectAllWoodTypeForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }
}