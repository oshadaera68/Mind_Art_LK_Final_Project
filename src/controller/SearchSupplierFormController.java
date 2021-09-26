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

public class SearchSupplierFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTeleNo;
    public JFXButton btnSearch;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idSupplierDeleteRegEx = Pattern.compile("^(S00-)[0-9]{3,4}$");

    public void initialize() {
        storeValidate();
        btnSearch.setDisable(true);
    }

    private void storeValidate() {
        map.put(txtId, idSupplierDeleteRegEx);
    }

    public void searchSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String customerId = txtId.getText();

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
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(Supplier s) {
        txtId.setText(s.getSupplierID());
        txtName.setText(s.getSupplierName());
        txtAddress.setText(s.getSupplierAddress());
        txtTeleNo.setText(s.getTelNo());
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSearch);

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