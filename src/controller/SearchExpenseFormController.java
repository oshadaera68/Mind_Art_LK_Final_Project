package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Employee;
import model.Expense;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchExpenseFormController {
    public JFXTextField txtExpenseID;
    public JFXTextField txtExpenseType;
    public JFXTextField txtExpenseAmount;

    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String customerId = txtExpenseID.getText();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Expense WHERE id=?");
        stm.setObject(1, txtExpenseID.getText());

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            Expense e1= new Expense(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3)
            );
            setData(e1);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    void setData(Expense e){
       txtExpenseID.setText(e.getId());
       txtExpenseType.setText(e.getType());
       txtExpenseAmount.setText(String.valueOf(e.getAmount()));

    }
}
