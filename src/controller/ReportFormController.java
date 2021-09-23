package controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportFormController {
    public AnchorPane context;

    public void dailyReportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("../view/Reports/DaliyReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JREmptyDataSource(1));
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void monthlyReportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("../view/Reports/MonthlyReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JREmptyDataSource(1));
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void annuallyReportOnAction(ActionEvent actionEvent) {

    }
}
