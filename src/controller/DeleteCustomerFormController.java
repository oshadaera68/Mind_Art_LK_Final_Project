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

public class DeleteCustomerFormController {
    public JFXTextField txtCusId;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtTel;
    public JFXButton btnDeleteCus;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern deleteCusIdRegEx = Pattern.compile("^(C00-)[0-9]{3,4}$");

    public void initialize() {
        btnDeleteCus.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtCusId, deleteCusIdRegEx);
    }

    public void deleteCusOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (delete(txtCusId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE CusID=?");
        stm.setObject(1, txtCusId.getText());
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

    void setData(Customer c) {
        txtCusId.setText(c.getCusID());
        txtCusName.setText(c.getCusName());
        txtCusAddress.setText(c.getCusAddress());
        txtTel.setText(c.getCusTelNo());
    }

    boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE CusID='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnDeleteCus);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }
}