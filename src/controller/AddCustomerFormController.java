package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddCustomerFormController {

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTelp;
    public JFXButton btnAdd;

    LinkedHashMap<TextField,Pattern> map=new LinkedHashMap<>();
    Pattern cusIDRegEx = Pattern.compile("^(C00-)[0-9]{3,4}$");
    Pattern cusNameRegEx = Pattern.compile("^[A-z ]{3,20}$");
    Pattern cusAddressRegEx = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern cusTelephoneRegEx = Pattern.compile("^0[0-9][0-9]?(-)?[0-9]{7}$");

    public void initialize(){
        btnAdd.setDisable(true);
        storeValidate();
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
            Object response = ValidationUtil.validate(map,btnAdd);

            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (response instanceof TextField) {
                    TextField errorText = (TextField) response;
                    errorText.requestFocus();
                } else if (response instanceof Boolean) {
                    new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
                }
            }
        }

    private void storeValidate() {
        map.put(txtId, cusIDRegEx);
        map.put(txtName, cusNameRegEx);
        map.put(txtAddress, cusAddressRegEx);
        map.put(txtTelp, cusTelephoneRegEx);
    }
}