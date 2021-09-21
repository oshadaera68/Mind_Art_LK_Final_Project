package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Item;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtQty;
    public JFXTextField txtUnit;

    public void addItemOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Item item = new Item(
                txtItemCode.getText(),txtItemName.getText(),
                Integer.parseInt(txtQty.getText()),Double.parseDouble(txtUnit.getText())
        );

        if(saveItem(item))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }


    boolean saveItem(Item c) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Item VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getItemCode());
        stm.setObject(2,c.getItemName());
        stm.setObject(3,c.getQtyOnHand());
        stm.setObject(4,c.getUnitPrice());

        return stm.executeUpdate()>0;

    }

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.setTitle("Timber Mill Management System - Ver 0.1.0");
        stage.show();
    }
}
