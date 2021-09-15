package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.WoodType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddWoodTypeFormController {
    public JFXTextField txtWoodTypeID;
    public JFXTextField txtWoodName;

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
}
