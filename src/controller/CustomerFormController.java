package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class CustomerFormController {
    public AnchorPane context;
//    public JFXButton btnUpdate;

    public void customerAddOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddCustomerForm");
    }

    public void searchOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("CustomerSearchForm");
    }

    public void customerAllOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("CustomerSelectAllForm");
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteCustomerForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void updateFormOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("CustomerUpdateForm");
    }
}