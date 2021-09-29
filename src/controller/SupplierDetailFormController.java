package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SupplierDetailFormController {
    public AnchorPane context;
    public AnchorPane supplierContexts;

    public void addSupplierDetailOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddSupplierDetailForm");
    }

    public void searchSupplierDetailOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SearchSupplierDetailForm");
    }

    public void selectAllFormOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SelectAllSupplierDetailForm");
    }

    public void deleteSupplierDetailsOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteSupplierDetailForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void backStageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) supplierContexts.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Timber Mill Management System - v0.1.0");
        window.show();
    }

    public void updateSupplierDetaliOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("UpdateSupplierDetailForm");
    }
}