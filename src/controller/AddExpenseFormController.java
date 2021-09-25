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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddExpenseFormController {
    public JFXTextField txtExpenseID;
    public JFXTextField txtExpenseType;
    public JFXTextField txtExpenseAmount;
    public JFXButton btnAddExp;
    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern expenseIDRegEx = Pattern.compile("^(E00-)[0-9]{3,4}$");
    Pattern expenseTypeRegEx = Pattern.compile("^[A-z ]{3,20}$");
    Pattern expenseAmountRegEx = Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");

    public void initialize(){
        btnAddExp.setDisable(true);
        storeValidate();
    }

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Expense ex1 = new Expense(
                txtExpenseID.getText(),txtExpenseType.getText(),
                Double.parseDouble(txtExpenseAmount.getText())
        );

        if(saveEmployee(ex1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    boolean saveEmployee(Expense ex) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Expense VALUES(?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,ex.getId());
        stm.setObject(2,ex.getType());
        stm.setObject(3,ex.getAmount());

        return stm.executeUpdate()>0;
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnAddExp);

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
        map.put(txtExpenseID, expenseIDRegEx);
        map.put(txtExpenseType, expenseTypeRegEx);
        map.put(txtExpenseAmount, expenseAmountRegEx);
    }
}