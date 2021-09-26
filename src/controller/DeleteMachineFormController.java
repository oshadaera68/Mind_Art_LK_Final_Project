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

public class DeleteMachineFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtModel;
    public JFXButton btnDeleteMachine;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern deleteExpenseIdRegEx = Pattern.compile("^(M00-)[0-9]{3,4}$");
    
    public void initialize(){
        btnDeleteMachine.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtId,deleteExpenseIdRegEx);
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (delete(txtId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchMachine(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Machinery WHERE MachineID=?");
        stm.setObject(1, txtId.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            Machine m1= new Machine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4)
            );
            setData(m1);
        }else{
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    void setData(Machine m){
        txtId.setText(m.getMachineID());
        txtName.setText(m.getMachineName());
        txtQty.setText(String.valueOf(m.getMachineQty()));
        txtModel.setText(m.getModel());
    }

    boolean delete(String id) throws ClassNotFoundException, SQLException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Machinery WHERE MachineID='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnDeleteMachine);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }
}