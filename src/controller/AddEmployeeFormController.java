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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddEmployeeFormController {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTelephone;
    public JFXButton btnAddEmp;
    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern empIDRegEx = Pattern.compile("^(E00-)[0-9]{3,4}$");
    Pattern empNameRegEx = Pattern.compile("^[A-z ]{3,20}$");
    Pattern empAddressRegEx = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern empTelephoneRegEx = Pattern.compile("^0[0-9][0-9]?(-)?[0-9]{7}$");

    public void initialize(){
        btnAddEmp.setDisable(true);
        storeValidate();
    }

    public void addEmployeeOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        Employee c1 = new Employee(
                txtID.getText(), txtName.getText(),
                txtAddress.getText(), txtTelephone.getText()
        );

        if (saveEmployee(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            txtID.clear();
            txtName.clear();
            txtAddress.clear();
            txtTelephone.clear();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    boolean saveEmployee(Employee e) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Employee VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,e.getEmpId());
        stm.setObject(2,e.getEmpName());
        stm.setObject(3,e.getAddress());
        stm.setObject(4,e.getTeleNo());

        return stm.executeUpdate()>0;
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnAddEmp);

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
        map.put(txtID, empIDRegEx);
        map.put(txtName, empNameRegEx);
        map.put(txtAddress, empAddressRegEx);
        map.put(txtTelephone, empTelephoneRegEx);
    }
}