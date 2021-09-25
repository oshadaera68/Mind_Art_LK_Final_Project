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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddSupplierDetailFormController {
    public JFXTextField txtSupID;
    public JFXTextField txtWoodId;
    public JFXTextField txtQty;
    public JFXTextField txtSize;
    public JFXButton btnAddSuppDetail;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern supDetailIDRegEx = Pattern.compile("^(S00-)[0-9]{3,4}$");
    Pattern woodTypeIDRegEx = Pattern.compile("^(W00-)[0-9]{3,4}$");
    Pattern QTYRegEx = Pattern.compile("^[0-9]{2,4}$");
    Pattern SizeRegEx = Pattern.compile("^[0-9]{2,4}$");

    public void initialize() {
        btnAddSuppDetail.setDisable(true);
        storeValidate();
    }

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SupplierDetail s1 = new SupplierDetail(
                txtSupID.getText(), txtWoodId.getText(),
                Integer.getInteger(txtQty.getText()), Integer.getInteger(txtSize.getText())
        );

        if (saveSupplierDetail(s1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    boolean saveSupplierDetail(SupplierDetail s) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO SupplierDetail VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, s.getSupplierOrderID());
        stm.setObject(2, s.getWoodTypeID());
        stm.setObject(3, s.getQTY());
        stm.setObject(4, s.getSize());

        return stm.executeUpdate() > 0;
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAddSuppDetail);

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
        map.put(txtSupID, supDetailIDRegEx);
        map.put(txtWoodId, woodTypeIDRegEx);
        map.put(txtQty, QTYRegEx);
        map.put(txtSize, SizeRegEx);
    }
}