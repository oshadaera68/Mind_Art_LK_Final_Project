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
        loadUi("AddMachineForm");
    }

    public void searchMachineOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SearchMachineForm");
    }

    public void deleteMachineOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteMachineForm");
    }

    public void selectAllMachineOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("MachineSelectAllForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }
}