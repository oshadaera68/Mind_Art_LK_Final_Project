package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddCustomerFormController {

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTelp;
    public JFXButton btnAdd;

    public void initialize() {
        btnAdd.setDisable(true);
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.setTitle("Timber Mill Management System - Ver 0.1.0");
        stage.show();
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                txtId.getText(), txtName.getText(), txtAddress.getText(), txtTelp.getText()
        );

        if (new CustomerController().addCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {

        String cusIDRegEx = "^(C00-)[0-9]{3,4}$";
        String cusNameRegEx = "^[A-z ]{3,30}$";
        String cusAddressRegEx = "^[A-z0-9/ ]{6,30}$";
        String cusTelephoneRegEx = "^0[0-9][0-9]?(-)?[0-9]{7}$";

        Pattern idCompile = Pattern.compile(cusIDRegEx);
        Pattern nameCompile = Pattern.compile(cusNameRegEx);
        Pattern addressCompile = Pattern.compile(cusAddressRegEx);
        Pattern telephoneCompile = Pattern.compile(cusTelephoneRegEx);

        String txtIdText = txtId.getText();

       if (idCompile.matcher(txtIdText).matches()) {
            new Alert(Alert.AlertType.CONFIRMATION, "OK").showAndWait();
            txtName.requestFocus();

        String txtNameText = txtName.getText();
        if (nameCompile.matcher(txtNameText).matches()) {
            new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
            txtAddress.requestFocus();
        } else {
            new Alert(Alert.AlertType.WARNING, "try again").showAndWait();
            txtName.requestFocus();
        }


           String txtAddressText = txtAddress.getText();
           if (addressCompile.matcher(txtAddressText).matches()) {
               new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
               txtTelp.requestFocus();
           } else {
               /*new Alert(Alert.AlertType.WARNING, "try again").showAndWait();
               txtAddress.requestFocus();*/
           }

           String txtTelpText = txtTelp.getText();
           if (telephoneCompile.matcher(txtTelpText).matches()){
               new Alert(Alert.AlertType.CONFIRMATION,"OK").show();
           }else{
               new Alert(Alert.AlertType.WARNING,"try again").show();
           }

       }else{
            new Alert(Alert.AlertType.WARNING, "try again").showAndWait();
            txtId.requestFocus();
            btnAdd.setDisable(true);
        }
    }




       /* String regEx = "";
        String txtId1 = txtId.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(txtId1).matches();

        String regEx1 = "";
        String textName1 = txtName.getText();
        Pattern compile1 = Pattern.compile(regEx1);
        boolean matches1 = compile1.matcher(textName1).matches();*/

        /* if (keyEvent.getCode() == KeyCode.ENTER) {
         */

            /*if (matches){

                txtName.requestFocus();
                // btnAdd.setDisable(false);
            }else{
                new Alert(Alert.AlertType.WARNING,"try again").showAndWait();
                btnAdd.setDisable(true);
            }

            if(keyEvent.getCode()== KeyCode.ENTER) {
                if (matches1) {
                    new Alert(Alert.AlertType.CONFIRMATION, "OK").showAndWait();
                    txtAddress.requestFocus();
                } else {
                    new Alert(Alert.AlertType.WARNING, "try again").showAndWait();
                    btnAdd.setDisable(true);
                }
            }
        }*/

    }
