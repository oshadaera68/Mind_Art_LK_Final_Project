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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class SearchItemFormController {
    public JFXTextField txtCode;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtUnitPrice;
    public JFXButton btnItem;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idItemDeleteRegEx = Pattern.compile("^(I00-)[0-9]{3,4}$");

    public void initialize(){
        storeValidate();
        btnItem.setDisable(true);
    }

    private void storeValidate() {
        map.put(txtCode,idItemDeleteRegEx);
    }

    public void searchItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtCode.getText();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE ItemCode=?");
        stm.setObject(1, txtCode.getText());

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            Item i1= new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
            setData(i1);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(Item i){
        txtCode.setText(i.getItemCode());
        txtName.setText(i.getItemName());
        txtQty.setText(String.valueOf(i.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnItem);

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
