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

public class SearchSupplierDetailFormController {
    public JFXTextField txtSupID;
    public JFXTextField txtWoodId;
    public JFXTextField txtQty;
    public JFXTextField txtSize;
    public JFXButton btnSearch;


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idSupplierDetailDeleteRegEx = Pattern.compile("^(S00-)[0-9]{3,4}$");


    public void initialize() {
        btnSearch.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtSupID,idSupplierDetailDeleteRegEx);
    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtSupID.getText();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM SupplierDetails WHERE SupplierOrderID=?");
        stm.setObject(1, txtSupID.getText());

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            SupplierDetail s1 = new SupplierDetail(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4)
            );
            setData(s1);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(SupplierDetail s) {
        txtSupID.setText(s.getSupplierOrderID());
        txtWoodId.setText(s.getWoodTypeID());
        txtQty.setText(String.valueOf(s.getQTY()));
        txtSize.setText(String.valueOf(s.getSize()));
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