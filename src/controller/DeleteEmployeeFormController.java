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

public class DeleteEmployeeFormController {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTelephone;
    public JFXButton btnDeleteEmployee;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern deleteEmpIdRegEx = Pattern.compile("^(E00-)[0-9]{3,4}$");

    public void initialize() {
        btnDeleteEmployee.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtID, deleteEmpIdRegEx);
    }

    public void deleteEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (delete(txtID.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee WHERE EmpId=?");
        stm.setObject(1, txtID.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Employee e1 = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            setData(e1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    void setData(Employee e) {
        txtID.setText(e.getEmpId());
        txtName.setText(e.getEmpName());
        txtAddress.setText(e.getAddress());
        txtTelephone.setText(e.getTeleNo());
    }

    boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Employee WHERE EmpId='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnDeleteEmployee);

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