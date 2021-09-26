package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Item;
import util.ValidationUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class DeleteItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtUnit;
    public JFXButton btnDeleteItem;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern deleteItemIdRegEx = Pattern.compile("^(I00-)[0-9]{3,4}$");

    public void initialize() {
        btnDeleteItem.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtItemCode, deleteItemIdRegEx);
    }

    public void searchItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE ItemCode=?");
        stm.setObject(1, txtItemCode.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Item i1 = new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
            setData(i1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    void setData(Item i) {
        txtItemCode.setText(i.getItemCode());
        txtName.setText(i.getItemName());
        txtQty.setText(String.valueOf(i.getQtyOnHand()));
        txtUnit.setText(String.valueOf(i.getUnitPrice()));
    }

    boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE ItemCode='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (delete(txtItemCode.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnDeleteItem);

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