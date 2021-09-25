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
    public AnchorPane contexts;

    public void addSupplierOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddSupplierForm");
    }

    public void searchSupplierOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SearchSupplierForm");
    }

    public void deleteSupplierOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteSupplierForm");
    }

    public void supplierTableOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SelectAllSupplerForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        contexts.getChildren().clear();
        contexts.getChildren().add(load);
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

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) contexts.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Timber Mill Management System - v0.1.0");
        window.show();
    }
}