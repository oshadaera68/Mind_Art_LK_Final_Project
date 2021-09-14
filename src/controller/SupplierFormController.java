package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class SupplierFormController {
    public AnchorPane context;

    public void addSupplierOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddSupplierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void searchSupplierOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SearchSupplierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void deleteSupplierOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteSupplierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void supplierTableOnAction(ActionEvent actionEvent) {

    }
}