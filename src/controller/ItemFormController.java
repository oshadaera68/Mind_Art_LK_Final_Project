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

public class ItemFormController {
    public AnchorPane context;

    public void addItemOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddItemForm");
    }

    public void searchItemOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SearchItemForm");
    }

    public void deleteItemOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteItemForm");
    }

    public void selectAllFormOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ItemSelectAllForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void updateItemOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("UpdateItemForm");
    }

    public void reportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("../view/report/SQLReport5.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}