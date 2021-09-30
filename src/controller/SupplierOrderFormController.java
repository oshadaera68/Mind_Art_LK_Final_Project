package controller;

import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

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

    public void updateSupplierOrderOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("UpdateSupplierOrderForm");
    }

    public void backStageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) supplierContexts.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Timber Mill Management System - v0.1.0");
        window.show();
    }
    public void reportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("../view/report/SQLReport10.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}