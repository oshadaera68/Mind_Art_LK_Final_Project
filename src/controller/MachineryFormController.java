package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class MachineryFormController {
    public AnchorPane context;

    public void addMachineOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddMachineForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void searchMachineOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MachineSearchForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void deleteMachineOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteMachineForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void selectAllMachineOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MachineSelectAllForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }
}