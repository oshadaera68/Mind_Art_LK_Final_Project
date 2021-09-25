package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SupplierOrderFormController {
    public AnchorPane supplierContexts;
    public AnchorPane context;

    public void addSupplierOrderOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddSupplierOrderForm");
    }

    public void searchSupplierOrderOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SearchSupplierOrderForm");
    }

    public void deleteSupplierOrderOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteSupplierOrderForm");
    }

    public void selectAllSupplierOrderOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SelectAllSupplierOrderForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) supplierContexts.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Supplier Form");
        window.show();
    }
}