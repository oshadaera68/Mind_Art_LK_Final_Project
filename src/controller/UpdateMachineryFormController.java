package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Machine;
import util.ValidationUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateMachineryFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtModel;
    public JFXButton btnUpdate;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern updateMachineRegEx = Pattern.compile("^(M00-)[0-9]{3,4}$");

    public void initialize() {
        btnUpdate.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtId, updateMachineRegEx);
    }

    public void searchMachine(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM machinery WHERE machineId=?");
        stm.setObject(1, txtId.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Machine m1 = new Machine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4)
            );
            setData(m1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnUpdate);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Machine m1 = new Machine(
                txtId.getText(), txtName.getText(),
                Integer.parseInt(txtQty.getText()),
                txtModel.getText()
        );

        if (update(m1))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
    }

    boolean update(Machine m) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE machinery SET machineName=?, machineQty=?, model=? WHERE machineId=?");
        stm.setObject(1, m.getMachineName());
        stm.setObject(2, m.getMachineQty());
        stm.setObject(3, m.getModel());
        stm.setObject(4, m.getMachineID());
        return stm.executeUpdate() > 0;
    }

    void setData(Machine c) {
        txtId.setText(c.getMachineID());
        txtName.setText(c.getMachineName());
        txtQty.setText(String.valueOf(c.getMachineQty()));
        txtModel.setText(c.getModel());
    }
}