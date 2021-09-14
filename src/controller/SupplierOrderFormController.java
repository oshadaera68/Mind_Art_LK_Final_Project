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

    public void addSupplierOrderOnAction(ActionEvent actionEvent) {

    }

    public void searchSupplierOrderOnAction(ActionEvent actionEvent) {

    }

    public void deleteSupplierOrderOnAction(ActionEvent actionEvent) {

    }

    public void selectAllSupplierOrderOnAction(ActionEvent actionEvent) {

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