package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Expense;
import model.Item;
import view.Tm.ExpenseTm;
import view.Tm.ItemTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectAllExpenseFormController {
    public TableView <ExpenseTm>tblExpense;
    public TableColumn colExpenseId;
    public TableColumn colExpenseType;
    public TableColumn colExpenseAmount;

    public void initialize() {
        try {

            colExpenseId.setStyle("-fx-alignment:CENTER;");
            colExpenseType.setStyle("-fx-alignment:CENTER;");
            colExpenseAmount.setStyle("-fx-alignment:CENTER");

            colExpenseId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colExpenseType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colExpenseAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

            loadAllItems();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllItems() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Expense");
        ResultSet rst = stm.executeQuery();
        ArrayList<Expense> Expense = new ArrayList<>();
        while (rst.next()) {
            Expense.add(new Expense(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3)
            ));
        }
        setItemsToTable(Expense);
    }

    private void setItemsToTable(ArrayList<Expense> customers) {
        ObservableList<ExpenseTm> obList = FXCollections.observableArrayList();
        customers.forEach(e->{
            obList.add(
                    new ExpenseTm(e.getId(), e.getType(),e.getAmount()));
        });
        tblExpense.setItems(obList);
    }
}