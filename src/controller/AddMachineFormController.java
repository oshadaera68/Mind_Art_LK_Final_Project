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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddMachineFormController {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtModel;
    public JFXButton btnAddMachine;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern machineIdRegEx = Pattern.compile("^(M00-)[0-9]{3,4}$");
    Pattern machineNameRegEx = Pattern.compile("^[A-z ]{3,30}$");
    Pattern machineQtyRegEx = Pattern.compile("^[0-9]{1,3}$");
    Pattern machineModelRegEx = Pattern.compile("^([A-Z]{3})?|([-a-z]{2,4})|([0-9]{3})$");

    public void initialize() {
        btnAddMachine.setDisable(true);
        storeValidate();
    }

    public void addMachineOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Machine m1 = new Machine(
                txtID.getText(), txtName.getText(),
                Integer.parseInt(txtQty.getText()), txtModel.getText()
        );

        if (saveMachine(m1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    boolean saveMachine(Machine m) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Machinery VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, m.getMachineID());
        stm.setObject(2, m.getMachineName());
        stm.setObject(3, m.getMachineQty());
        stm.setObject(4, m.getModel());

        return stm.executeUpdate() > 0;
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAddMachine);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }

    private void storeValidate() {
        map.put(txtID, machineIdRegEx);
        map.put(txtName, machineNameRegEx);
        map.put(txtQty, machineQtyRegEx);
        map.put(txtModel, machineModelRegEx);
    }
}