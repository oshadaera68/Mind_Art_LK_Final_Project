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

public class UpdateItemFormController {
    public JFXTextField txtCode;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtUnitPrice;
    public JFXButton btnItemUpdate;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern itemIdRegEx = Pattern.compile("^(I00-)[0-9]{3,4}$");
    Pattern itemNameRegEx = Pattern.compile("^[A-z ]{3,20}$");
    Pattern itemQtyRegEx = Pattern.compile("^[0-9]{2,4}$");
    Pattern itemUnitPriceRegEx = Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");

    public void initialize() {
        btnItemUpdate.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtCode, itemIdRegEx);
        map.put(txtName,itemNameRegEx);
        map.put(txtQty,itemQtyRegEx);
        map.put(txtUnitPrice,itemUnitPriceRegEx);
    }

    public void searchItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE itemCode=?");
        stm.setObject(1, txtCode.getText());
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

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnItemUpdate);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }

    public void updateItemOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Item c1 = new Item(
                txtCode.getText(), txtName.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtUnitPrice.getText())
        );

        if (update(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
    }

    boolean update(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET itemName=?, qtyOnHand=?, unitPrice=? WHERE itemCode=?");
        stm.setObject(1, i.getItemName());
        stm.setObject(2, i.getQtyOnHand());
        stm.setObject(3, i.getUnitPrice());
        stm.setObject(4, i.getItemCode());
        return stm.executeUpdate() > 0;
    }

    void setData(Item i) {
        txtCode.setText(i.getItemCode());
        txtName.setText(i.getItemName());
        txtQty.setText(String.valueOf(i.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
    }
}