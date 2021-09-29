package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.SupplierDetail;
import util.ValidationUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateSupplierDetailFormController {
    public JFXTextField txtSupID;
    public JFXTextField txtWoodId;
    public JFXTextField txtQty;
    public JFXTextField txtSize;
    public JFXButton btnUpdate;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern supplierDetailUpdateRegEx = Pattern.compile("^(S00-)[0-9]{3,4}$");

    public void initialize() {
        btnUpdate.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtSupID, supplierDetailUpdateRegEx);
    }

    public void searchId(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM supplierDetail WHERE supplierOrderId=?");
        stm.setObject(1, txtSupID.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            SupplierDetail s1 = new SupplierDetail(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
            setData(s1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnUpdate);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }

    public void updateSupplierDetailOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SupplierDetail c1 = new SupplierDetail(
                txtSupID.getText(), txtWoodId.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtSize.getText())
        );


        if (update(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again").show();


    }

    boolean update(SupplierDetail s) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE supplierDetail SET woodTypeId=?, qty=?, size=? WHERE supplierOrderId=?");
        stm.setObject(1, s.getWoodTypeID());
        stm.setObject(2, s.getQTY());
        stm.setObject(3, s.getSize());
        stm.setObject(4, s.getSupplierOrderID());
        return stm.executeUpdate() > 0;
    }

    void setData(SupplierDetail s) {
        txtSupID.setText(s.getSupplierOrderID());
        txtWoodId.setText(s.getWoodTypeID());
        txtQty.setText(String.valueOf(s.getQTY()));
        txtSize.setText(String.valueOf(s.getSize()));
    }
}