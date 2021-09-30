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

public class WoodTypeFormController {
    public AnchorPane context;

    public void addWoodTypeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddWoodTypeForm");
    }

    public void searchWoodTypeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SearchWoodTypeForm");
    }

    public void deleteWoodTypeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteWoodTypeForm");
    }

    public void selectAllWoodTypeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SelectAllWoodTypeForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void updateWoodTypeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("UpdateWoodTypeForm");
    }

    public void reportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("../view/report/SQLReport3.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}