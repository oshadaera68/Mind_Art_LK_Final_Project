package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.SupplierOrder;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchSupplierOrderFormController {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtDate;
    public AnchorPane searchContext;

    public void searchSupplierOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String customerId = txtID.getText();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM SupplierOrder WHERE SupplierOrderID=?");
        stm.setObject(1, txtID.getText());

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            SupplierOrder s1= new SupplierOrder(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            setData(s1);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(SupplierOrder supplierOrder){
        txtID.setText(supplierOrder.getSupplierOrderID());
        txtName.setText(supplierOrder.getSupplierOrderName());
        txtDate.setText(supplierOrder.getDate());
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) searchContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Supplier Order Form");
        window.show();
    }
}