package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Employee;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchEmployeeFormController {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTelephone;

    public void searchEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String customerId = txtID.getText();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee WHERE id=?");
        stm.setObject(1, txtID.getText());

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            Employee e1= new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            setData(e1);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(Employee e){
        txtID.setText(e.getEmpId());
        txtName.setText(e.getEmpName());
        txtAddress.setText(e.getAddress());
        txtTelephone.setText(e.getTeleNo());
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.setTitle("Timber Mill Management System - Ver 0.1.0");
        stage.show();
    }
}