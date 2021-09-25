package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Item;
import util.ValidationUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtQty;
    public JFXTextField txtUnit;
    public JFXButton btnItemAdd;

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern itemIdRegEx = Pattern.compile("^(I00-)[0-9]{3,4}$");
    Pattern itemNameRegEx = Pattern.compile("^[A-z ]{3,20}$");
    Pattern itemQtyRegEx = Pattern.compile("^[0-9]{2,4}$");
    Pattern itemUnitPriceRegEx = Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");

    public void initialize(){
        btnItemAdd.setDisable(true);
        storeValidate();
    }

    public void addItemOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Item item = new Item(
                txtItemCode.getText(),txtItemName.getText(),
                Integer.parseInt(txtQty.getText()),Double.parseDouble(txtUnit.getText())
        );

        if(saveItem(item))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }


    boolean saveItem(Item c) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Item VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getItemCode());
        stm.setObject(2,c.getItemName());
        stm.setObject(3,c.getQtyOnHand());
        stm.setObject(4,c.getUnitPrice());

        return stm.executeUpdate()>0;

    }
    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnItemAdd);

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
        map.put(txtItemCode, itemIdRegEx);
        map.put(txtItemName, itemNameRegEx);
        map.put(txtQty, itemQtyRegEx);
        map.put(txtUnit, itemUnitPriceRegEx);
    }
}
