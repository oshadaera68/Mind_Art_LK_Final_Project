package controller;

import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

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

    public void updateMachineOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("UpdateMachineryForm");
    }

    public void reportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("../view/report/SQLReport6.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}