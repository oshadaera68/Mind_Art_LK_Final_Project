package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.SupplierOrder;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class DeleteSupplierOrderFormController {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtDate;
    public AnchorPane deleteContext;
    public JFXButton btnDeleteOrder;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern deleteSupOrderIDRegEx = Pattern.compile("^(S00-)[0-9]{3,4}$");

    public void initialize() {
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtID, deleteSupOrderIDRegEx);
    }

    public void searchOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM SupplierOrder WHERE SupplierOrderID=?");
        stm.setObject(1, txtID.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            SupplierOrder s1 = new SupplierOrder(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            setData(s1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    void setData(SupplierOrder supplierOrder) {
        txtID.setText(supplierOrder.getSupplierOrderID());
        txtName.setText(supplierOrder.getSupplierOrderName());
        txtDate.setText(supplierOrder.getDate());
    }

    boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM SupplierOrder WHERE SupplierOrderID='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteSupplierOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (delete(txtID.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SupplierOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) deleteContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.setTitle("Supplier Order Form");
        window.show();
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnDeleteOrder);

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