package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteCustomerFormController {
    public JFXTextField txtCusId;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtTel;

    public void deleteCusOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (delete(txtCusId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.setTitle("Timber Mill Management System - Ver 0.1.0");
        stage.show();
    }

    public void searchCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE CusID=?");
        stm.setObject(1, txtCusId.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            Customer c1= new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            setData(c1);
        }else{
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }

    }

    void setData(Customer c){
        txtCusId.setText(c.getCusID());
        txtCusName.setText(c.getCusName());
        txtCusAddress.setText(c.getCusAddress());
        txtTel.setText(c.getCusTelNo());
    }

    boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE CusID='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
}