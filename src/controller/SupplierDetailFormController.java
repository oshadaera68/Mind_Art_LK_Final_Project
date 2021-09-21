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

    public void selectAllFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SelectAllSupplierDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void deleteSupplierDetailsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteSupplierDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void backStageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) supplierContexts.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Supplier Form");
        window.show();
    }
}