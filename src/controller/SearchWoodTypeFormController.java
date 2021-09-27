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

public class SearchWoodTypeFormController {
    public JFXTextField txtWoodTypeID;
    public JFXTextField txtWoodName;
    public JFXButton btnSearch;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idWoodDeleteRegEx = Pattern.compile("^(W00-)[0-9]{3,4}$");

    public void initialize(){
        storeValidate();
        btnSearch.setDisable(true);
    }

    private void storeValidate() {
        map.put(txtWoodTypeID,idWoodDeleteRegEx);
    }

    public void searchWoodOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtWoodTypeID.getText();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM WoodType WHERE WoodTypeID=?");
        stm.setObject(1, txtWoodTypeID.getText());

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            WoodType w1= new WoodType(
                    rst.getString(1),
                    rst.getString(2)
            );
            setData(w1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(WoodType w){
        txtWoodTypeID.setText(w.getWoodTypeID());
        txtWoodName.setText(w.getWoodName());
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