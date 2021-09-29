package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Employee;
import util.ValidationUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateEmployeeFormController {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTelephone;
    public JFXButton btnUpdateEmp;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern deleteEmpIdRegEx = Pattern.compile("^(E00-)[0-9]{3,4}$");

    public void initialize() {
        btnUpdateEmp.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtID, deleteEmpIdRegEx);
    }

    public void searchEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee WHERE empId=?");
        stm.setObject(1, txtID.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Employee c1 = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            setData(c1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }

    }

    public void updateEmpOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Employee c1 = new Employee(
                txtID.getText(), txtName.getText(),
                txtAddress.getText(),
                txtTelephone.getText()
        );

        if (update(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
    }

    boolean update(Employee e) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Employee SET empName=?, empAddress=?, teleNo=? WHERE empId=?");
        stm.setObject(1, e.getEmpName());
        stm.setObject(2, e.getAddress());
        stm.setObject(3, e.getTeleNo());
        stm.setObject(4, e.getEmpId());
        return stm.executeUpdate() > 0;
    }

    void setData(Employee e) {
        txtID.setText(e.getEmpId());
        txtName.setText(e.getEmpName());
        txtAddress.setText(e.getAddress());
        txtTelephone.setText(e.getEmpId());
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnUpdateEmp);

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