package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class SupplierDetailFormController {
    public AnchorPane context;

    public void addSupplierDetailOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddSupplierDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void searchSupplierDetailOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SearchSupplierDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void selectAllFormOnAction(ActionEvent actionEvent) {

    }

    public void deleteSupplierDetailsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteSupplierDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }
}