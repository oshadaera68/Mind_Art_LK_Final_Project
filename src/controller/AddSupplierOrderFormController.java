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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddSupplierOrderFormController {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtDate;
    public AnchorPane addContext;

    public void addSupplierOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SupplierOrder c1 = new SupplierOrder(
                txtID.getText(),txtName.getText(),
                txtDate.getText()
        );

        if(saveSupplierOrder(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    boolean saveSupplierOrder(SupplierOrder supplierOrder) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO SupplierOrder VALUES(?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,supplierOrder.getSupplierOrderID());
        stm.setObject(2,supplierOrder.getSupplierOrderName());
        stm.setObject(3,supplierOrder.getDate());

        return stm.executeUpdate()>0;
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) addContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Supplier Order Form");
        window.show();
    }
}