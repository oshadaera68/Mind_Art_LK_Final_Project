package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.SupplierDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddSupplierDetailFormController {
    public JFXTextField txtSupID;
    public JFXTextField txtWoodId;
    public JFXTextField txtQty;
    public JFXTextField txtSize;

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SupplierDetail s1 = new SupplierDetail(
                txtSupID.getText(),txtWoodId.getText(),
                Integer.getInteger(txtQty.getText()),Integer.getInteger(txtSize.getText())
        );

        if(saveSupplierDetail(s1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    boolean saveSupplierDetail(SupplierDetail s) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO SupplierDetail VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,s.getSupplierOrderID());
        stm.setObject(2,s.getWoodTypeID());
        stm.setObject(3,s.getQTY());
        stm.setObject(4,s.getSize());

        return stm.executeUpdate()>0;

    }
}