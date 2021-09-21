package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Supplier;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchSupplierFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTeleNo;

    public void searchSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String customerId = txtId.getText();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier WHERE SupplierID=?");
        stm.setObject(1, txtId.getText());

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            Supplier s1= new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            setData(s1);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(Supplier s){
        txtId.setText(s.getSupplierID());
        txtName.setText(s.getSupplierName());
        txtAddress.setText(s.getSupplierAddress());
        txtTeleNo.setText(s.getTelNo());
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.setTitle("Timber Mill Management System - Ver 0.1.0");
        stage.show();
    }
}