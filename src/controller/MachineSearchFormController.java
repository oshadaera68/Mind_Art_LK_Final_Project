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

public class MachineSearchFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtModel;
    public JFXButton btnSearch;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern searchMachineRegEx = Pattern.compile("^(M00-)[0-9]{3,4}$");

    public void initialize() {
        btnSearch.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtId, searchMachineRegEx);
    }

    public void searchMachineOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtId.getText();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Machinery WHERE MachineID=?");
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
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(Machine m) {
        txtId.setText(m.getMachineID());
        txtName.setText(m.getMachineName());
        txtQty.setText(String.valueOf(m.getMachineQty()));
        txtModel.setText(m.getModel());
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSearch);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }
}