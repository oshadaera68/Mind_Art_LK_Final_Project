package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class CustomerSearchFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTel;
    public JFXButton btnSearchForm;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern searchCusRegEx = Pattern.compile("^(C00-)[0-9]{3,4}$");

    public void initialize() {
        btnSearchForm.setDisable(true);
        storeValidate();
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String txtIdText = txtId.getText();

        Customer c1 = new CustomerController().getCustomer(txtIdText);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }

    void setData(Customer c) {
        txtId.setText(c.getCusID());
        txtName.setText(c.getCusName());
        txtAddress.setText(c.getCusAddress());
        txtTel.setText(c.getCusTelNo());
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSearchForm);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }

    private void storeValidate() {
        map.put(txtId, searchCusRegEx);
    }
}