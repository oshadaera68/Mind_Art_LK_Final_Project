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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtUnit;

    public void searchItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE ItemCode=?");
        stm.setObject(1, txtItemCode.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            Item i1= new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
            setData(i1);
        }else{
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    void setData(Item i){
        txtItemCode.setText(i.getItemCode());
        txtName.setText(i.getItemName());
        txtQty.setText(String.valueOf(i.getQtyOnHand()));
        txtUnit.setText(String.valueOf(i.getUnitPrice()));
    }

    boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE ItemCode='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public void deleteItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (delete(txtItemCode.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.setTitle("Timber Mill Management System - Ver 0.1.0");
        stage.show();
    }
}