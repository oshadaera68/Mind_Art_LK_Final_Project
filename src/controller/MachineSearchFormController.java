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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MachineSearchFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtModel;

    public void searchMachineOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtId.getText();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Machinery WHERE MachineID=?");
        stm.setObject(1, txtId.getText());

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            Machine m1= new Machine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4)
            );
            setData(m1);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(Machine m) {
        txtId.setText(m.getMachineID());
        txtName.setText(m.getMachineName());
        txtQty.setText(String.valueOf(m.getMachineQty()));
        txtModel.setText(m.getModel());
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.setTitle("Timber Mill Management System - Ver 0.1.0");
        stage.show();
    }
}
