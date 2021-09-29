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

public class UpdateExpenseFormController {
    public JFXTextField txtExpenseID;
    public JFXTextField txtExpenseType;
    public JFXButton btnUpdateExpense;
    public JFXTextField txtExpenseAmount;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern expenseIDRegEx = Pattern.compile("^(EX00-)[0-9]{3,4}$");

    public void initialize() {
        btnUpdateExpense.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtExpenseID, expenseIDRegEx);
    }

    public void searchExpense(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Expense WHERE expenId=?");
        stm.setObject(1, txtExpenseID.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Expense c1 = new Expense(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3)
            );
            setData(c1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnUpdateExpense);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Expense e1 = new Expense(
                txtExpenseID.getText(), txtExpenseType.getText(),
                txtExpenseAmount.getText()
        );

        if (update(e1))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
    }

    boolean update(Expense e) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Expense SET expenType=?, expenAmount=? WHERE expenId=?");
        stm.setObject(1, e.getType());
        stm.setObject(2, e.getAmount());
        stm.setObject(3, e.getId());
        return stm.executeUpdate() > 0;
    }

    void setData(Expense e) {
        txtExpenseID.setText(e.getId());
        txtExpenseType.setText(e.getType());
        txtExpenseAmount.setText(String.valueOf(e.getAmount()));
    }
}