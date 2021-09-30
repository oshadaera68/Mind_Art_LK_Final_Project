package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Supplier;
import util.ValidationUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateSupplierFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTeleNo;
    public JFXButton btnSup;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern supplierUpdateRegEx = Pattern.compile("^(S00-)[0-9]{3,4}$");
    Pattern supNameRegEx = Pattern.compile("^[A-z ]{3,20}$");
    Pattern supAddressRegEx = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern supTelNoRegEx = Pattern.compile("^0[0-9][0-9]?(-)?[0-9]{7}$");

    public void initialize() {
        btnSup.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtId, supplierUpdateRegEx);
        map.put(txtName, supNameRegEx);
        map.put(txtAddress, supAddressRegEx);
        map.put(txtTeleNo, supTelNoRegEx);
    }

    public void searchSupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM supplier WHERE supplierId=?");
        stm.setObject(1, txtId.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Supplier s1 = new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            setData(s1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSup);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }

    public void updateSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Supplier s1 = new Supplier(
                txtId.getText(), txtName.getText(),
                txtAddress.getText(),
                txtTeleNo.getText()
        );

        if (update(s1))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
    }

    boolean update(Supplier s) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE supplier SET supplierName=?, supplierAddress=?, telNo=? WHERE supplierId=?");
        stm.setObject(1, s.getSupplierName());
        stm.setObject(2, s.getSupplierAddress());
        stm.setObject(3, s.getTelNo());
        stm.setObject(4, s.getSupplierID());
        return stm.executeUpdate() > 0;
    }

    void setData(Supplier s) {
        txtId.setText(s.getSupplierID());
        txtName.setText(s.getSupplierName());
        txtAddress.setText(s.getSupplierAddress());
        txtTeleNo.setText(s.getTelNo());
    }
}