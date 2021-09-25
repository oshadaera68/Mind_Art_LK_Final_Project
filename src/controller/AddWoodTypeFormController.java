package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.WoodType;
import util.ValidationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddWoodTypeFormController {
    public JFXTextField txtWoodTypeID;
    public JFXTextField txtWoodName;
    public JFXButton btnAddWood;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idWoodRegEx = Pattern.compile("^(W00-)[0-9]{3,4}$");
    Pattern woodNameRegEx = Pattern.compile("^[A-z ]{3,20}$");

    public void initialize() {
        btnAddWood.setDisable(true);
        storeValidate();
    }

    public void addWoodOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        WoodType c1 = new WoodType(
                txtWoodTypeID.getText(),txtWoodName.getText()
        );

        if(saveWoodType(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    boolean saveWoodType(WoodType c) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO WoodType VALUES(?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getWoodTypeID());
        stm.setObject(2,c.getWoodName());

        return stm.executeUpdate()>0;
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAddWood);

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
        map.put(txtWoodTypeID, idWoodRegEx);
        map.put(txtWoodName, woodNameRegEx);
    }
}
