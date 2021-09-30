package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;
import util.ValidationUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class CustomerUpdateFormController {
    public JFXTextField txtId;
    public JFXTextField txtAddress;
    public JFXTextField txtTel;
    public JFXButton btnUpdate;
    public JFXTextField txtName;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern cusIDRegEx = Pattern.compile("^(C00-)[0-9]{3,4}$");
    Pattern cusNameRegEx = Pattern.compile("^[A-z ]{3,20}$");
    Pattern cusAddressRegEx = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern cusTelephoneRegEx = Pattern.compile("^0[0-9][0-9]?(-)?[0-9]{7}$");


    public void initialize() {
        btnUpdate.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtId,cusIDRegEx);
        map.put(txtName,cusNameRegEx);
        map.put(txtAddress,cusAddressRegEx);
        map.put(txtTel,cusTelephoneRegEx);
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnUpdate);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1 = new Customer(
                txtId.getText(), txtName.getText(),
                txtAddress.getText(),
                txtTel.getText()
        );

        if (update(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
    }

    boolean update(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET cusName=?, cusAddress=?, cusTelNo=? WHERE cusId=?");
        stm.setObject(1, c.getCusName());
        stm.setObject(2, c.getCusAddress());
        stm.setObject(3, c.getCusTelNo());
        stm.setObject(4, c.getCusID());
        return stm.executeUpdate() > 0;
    }

    void setData(Customer c) {
        txtId.setText(c.getCusID());
        txtName.setText(c.getCusName());
        txtAddress.setText(c.getCusAddress());
        txtTel.setText(c.getCusTelNo());
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE cusId=?");
        stm.setObject(1, txtId.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Customer c1 = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            setData(c1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }
}