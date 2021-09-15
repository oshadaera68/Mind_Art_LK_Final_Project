package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.WoodType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchWoodTypeFormController {
    public JFXTextField txtWoodTypeID;
    public JFXTextField txtWoodName;

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
}