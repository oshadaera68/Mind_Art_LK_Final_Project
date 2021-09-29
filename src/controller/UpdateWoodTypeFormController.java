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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateWoodTypeFormController {
    public JFXTextField txtWoodTypeID;
    public JFXTextField txtWoodName;
    public JFXButton btnUpdate;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern updateWoodTypeRegEx = Pattern.compile("^(W00-)[0-9]{3,4}$");

    public void initialize(){
        btnUpdate.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtWoodTypeID,updateWoodTypeRegEx);
    }

    public void searchID(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM woodType WHERE woodTypeId=?");
        stm.setObject(1, txtWoodTypeID.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            WoodType w1= new WoodType(
                    rst.getString(1),
                    rst.getString(2)
            );
            setData(w1);
        }else{
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnUpdate);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        WoodType w1= new WoodType(
                txtWoodTypeID.getText(),txtWoodName.getText()
        );

        if (update(w1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    boolean update(WoodType w) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE woodType SET woodName=? WHERE woodTypeId=?");
        stm.setObject(1,w.getWoodName());
        stm.setObject(2,w.getWoodTypeID());
        return stm.executeUpdate()>0;
    }

    void setData(WoodType w){
        txtWoodTypeID.setText(w.getWoodTypeID());
        txtWoodName.setText(w.getWoodName());
    }
}