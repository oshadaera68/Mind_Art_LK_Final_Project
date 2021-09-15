package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SupplierFormController {
    public AnchorPane context;
    public AnchorPane contexts;

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

    public void supplierTableOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierSelectAllForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void supplierOrderFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) contexts.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Supplier Order Form");
        window.show();
    }

    public void supplierDetailsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) contexts.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Supplier Detail Form");
        window.show();
    }
}