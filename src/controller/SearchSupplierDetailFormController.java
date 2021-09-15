package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.SupplierDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchSupplierDetailFormController {
    public JFXTextField txtSupID;
    public JFXTextField txtWoodId;
    public JFXTextField txtQty;
    public JFXTextField txtSize;

    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtSupID.getText();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM SupplierDetails WHERE SupplierOrderID=?");
        stm.setObject(1, txtSupID.getText());

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            SupplierDetail s1= new SupplierDetail(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4)
            );
            setData(s1);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(SupplierDetail s){
        txtSupID.setText(s.getSupplierOrderID());
        txtWoodId.setText(s.getWoodTypeID());
        txtQty.setText(String.valueOf(s.getQTY()));
        txtSize.setText(String.valueOf(s.getSize()));
    }
}

