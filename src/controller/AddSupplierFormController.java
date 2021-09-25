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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddSupplierFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTeleNo;
    public JFXButton btnAddSupp;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern supIdRegEx = Pattern.compile("^(S00-)[0-9]{3,4}$");
    Pattern supNameRegEx = Pattern.compile("^[A-z ]{3,20}$");
    Pattern supAddressRegEx = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern supTelNoRegEx = Pattern.compile("^0[0-9][0-9]?(-)?[0-9]{7}$");

    public void initialize() {
        btnAddSupp.setDisable(true);
        storeValidate();
    }

    public void addSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Supplier s1 = new Supplier(
                txtId.getText(), txtName.getText(),
                txtAddress.getText(), txtTeleNo.getText()
        );

        if (saveSupplier(s1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }


    boolean saveSupplier(Supplier s) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Supplier VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, s.getSupplierID());
        stm.setObject(2, s.getSupplierName());
        stm.setObject(3, s.getSupplierAddress());
        stm.setObject(4, s.getTelNo());

        return stm.executeUpdate() > 0;
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAddSupp);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }

    private void storeValidate() {
        map.put(txtId, supIdRegEx);
        map.put(txtName, supNameRegEx);
        map.put(txtAddress, supAddressRegEx);
        map.put(txtTeleNo, supTelNoRegEx);
    }
}