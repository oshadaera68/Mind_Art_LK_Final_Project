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
import model.EmployeePayment;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeePaymentController {

    public JFXTextField txtID;
    public JFXTextField txtDate;
    public JFXTextField txtAmount;
    public AnchorPane context;

    public void addPaymentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        EmployeePayment c1 = new EmployeePayment(
                txtID.getText(),txtDate.getText(),
                Double.parseDouble(txtAmount.getText())
        );

        if(saveEmployeePayment(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    boolean saveEmployeePayment(EmployeePayment e) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO EmployeePayment VALUES(?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,e.getId());
        stm.setObject(2,e.getDate());
        stm.setObject(3,e.getAmount());

        return stm.executeUpdate()>0;
    }

    public void backWardOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/EmployeeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) context.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Employee Form");
        window.show();
    }
}