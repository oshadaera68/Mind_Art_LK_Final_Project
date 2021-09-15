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
        URL resource = getClass().getResource("../view/AddWoodTypeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void searchWoodTypeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SearchWoodTypeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void deleteWoodTypeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteWoodTypeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void selectAllWoodTypeOnAction(ActionEvent actionEvent) {

    }
}
