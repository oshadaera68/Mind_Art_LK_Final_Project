package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Employee;
import model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddExpenseFormController {
    public JFXTextField txtExpenseID;
    public JFXTextField txtExpenseType;
    public JFXTextField txtExpenseAmount;

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
        String query="INSERT INTO Expense VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,ex.getId());
        stm.setObject(2,ex.getType());
        stm.setObject(3,ex.getAmount());

        return stm.executeUpdate()>0;
    }
}