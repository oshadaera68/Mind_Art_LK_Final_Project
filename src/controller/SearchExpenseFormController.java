package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Expense;
import util.ValidationUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class SearchExpenseFormController {
    public JFXTextField txtExpenseID;
    public JFXTextField txtExpenseType;
    public JFXTextField txtExpenseAmount;
    public JFXButton btnSearch;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idExpenseDeleteRegEx = Pattern.compile("^(EX00-)[0-9]{3,4}$");

    public void initialize() {
        storeValidate();
        btnSearch.setDisable(true);
    }

    private void storeValidate() {
        map.put(txtExpenseID, idExpenseDeleteRegEx);
    }


    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String customerId = txtExpenseID.getText();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Expense WHERE id=?");
        stm.setObject(1, txtExpenseID.getText());

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            Expense e1 = new Expense(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3)
            );
            setData(e1);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(Expense e) {
        txtExpenseID.setText(e.getId());
        txtExpenseType.setText(e.getType());
        txtExpenseAmount.setText(String.valueOf(e.getAmount()));
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSearch);

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