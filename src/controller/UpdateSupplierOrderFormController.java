package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.SupplierOrder;
import util.ValidationUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateSupplierOrderFormController {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtDate;
    public JFXButton btnUpdate;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern updateSupplierOrderIdRegEx = Pattern.compile("^(S00-)[0-9]{3,4}$");
    Pattern supOrderNameRegEx = Pattern.compile("^(W00-)[0-9]{3,4}$");
    Pattern supDateRegEx = Pattern.compile("^([0-9]{4}/[0-9]{2}/[0-9]{2})|([0-9]{4}.[0-9]{2}.[0-9]{2})$");

    public void initialize() {
        btnUpdate.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtID, updateSupplierOrderIdRegEx);
        map.put(txtName, supOrderNameRegEx);
        map.put(txtDate, supDateRegEx);
    }

    public void searchOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM supplierOrder WHERE supplierOrderId=?");
        stm.setObject(1, txtID.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            SupplierOrder s1 = new SupplierOrder(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
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

    public void updateSupplierOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SupplierOrder s1 = new SupplierOrder(
                txtID.getText(), txtName.getText(),
                txtDate.getText()
        );

        if (update(s1))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
    }

    boolean update(SupplierOrder s) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE supplierOrder SET supplierOrderName=?, supplierOrderDate=? WHERE supplierOrderId=?");
        stm.setObject(1, s.getSupplierOrderName());
        stm.setObject(2, s.getDate());
        stm.setObject(3, s.getSupplierOrderID());
        return stm.executeUpdate() > 0;
    }

    void setData(SupplierOrder s) {
        txtID.setText(s.getSupplierOrderID());
        txtName.setText(s.getSupplierOrderName());
        txtDate.setText(s.getDate());
    }
}