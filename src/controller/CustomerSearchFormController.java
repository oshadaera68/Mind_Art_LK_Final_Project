package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerSearchFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTel;

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.setTitle("Timber Mill Management System - Ver 0.1.0");
        stage.show();
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String txtIdText = txtId.getText();

        Customer c1 = new CustomerController().getCustomer(txtIdText);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else{
            setData(c1);
        }
    }

    void setData(Customer c){
        txtId.setText(c.getCusID());
        txtName.setText(c.getCusName());
        txtAddress.setText(c.getCusAddress());
        txtTel.setText(c.getCusTelNo());
    }
}