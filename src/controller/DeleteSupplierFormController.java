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

public class DeleteSupplierFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTeleNo;
    public JFXButton btnSup;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern deleteSupIDRegEx = Pattern.compile("^(S00-)[0-9]{3,4}$");

    public void initialize() {
        btnSup.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtId, deleteSupIDRegEx);
    }

    public void searchSupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier WHERE SupplierID=?");
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

    void setData(Supplier s) {
        txtId.setText(s.getSupplierID());
        txtName.setText(s.getSupplierName());
        txtAddress.setText(s.getSupplierAddress());
        txtTeleNo.setText(s.getTelNo());
    }

    boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Supplier WHERE SupplierID='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (delete(txtId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSup);

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