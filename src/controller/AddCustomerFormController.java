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

public class AddCustomerFormController {

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTelp;

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.setTitle("Timber Mill Management System - Ver 0.1.0");
        stage.show();
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                txtId.getText(),txtName.getText(),txtAddress.getText(),txtTelp.getText()
        );

        if(new CustomerController().addCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }
}