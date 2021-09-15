package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Machine;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddMachineFormController {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtModel;

    public void addMachineOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Machine m1 = new Machine(
                txtID.getText(),txtName.getText(),
                Integer.parseInt(txtQty.getText()),txtModel.getText()
        );

        if(saveMachine(m1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    boolean saveMachine(Machine m) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Machine VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,m.getMachineID());
        stm.setObject(2,m.getMachineName());
        stm.setObject(3,m.getMachineQty());
        stm.setObject(4,m.getModel());

        return stm.executeUpdate()>0;

    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
        stage.setTitle("Timber Mill Management System - Ver 0.1.0");
        stage.show();
    }
}