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
        loadUi("SupplierSelectAllForm");
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

    public void updateSupplierOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("UpdateSupplierForm");
    }

    public void reportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("../view/report/SQLReport4.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}