package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Expense;
import model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteExpenseFormController {

    public JFXTextField txtExpenseID;
    public JFXTextField txtExpenseType;
    public JFXTextField txtExpenseAmount;

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (delete(txtExpenseID.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchExpense(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Expense WHERE ExpenID=?");
        stm.setObject(1, txtExpenseID.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            Expense e1= new Expense(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3)
            );
            setData(e1);
        }else{
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }
    }

    void setData(Expense e){
        txtExpenseID.setText(e.getId());
        txtExpenseType.setText(e.getType());
        txtExpenseAmount.setText(String.valueOf(e.getAmount()));
    }

    boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Expense WHERE ExpenID='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
}