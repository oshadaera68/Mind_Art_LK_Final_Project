package controller;

import com.jfoenix.controls.JFXButton;
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

    public void reportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("../view/report/SQLReport.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}