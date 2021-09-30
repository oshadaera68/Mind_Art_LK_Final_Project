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

public class EmployeeFormController {
    public AnchorPane contexts;

    public void addEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddEmployeeForm");
    }

    public void searchEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SearchEmployeeForm");
    }

    public void deleteEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteEmployeeForm");
    }

    public void selectAllEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("EmployeeSelectAllForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        contexts.getChildren().clear();
        contexts.getChildren().add(load);
    }

    public void employeePaymentFormOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("EmployeePaymentForm");
    }

    public void updateEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("UpdateEmployeeForm");
    }

    public void reportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("../view/report/SQLReport2.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}