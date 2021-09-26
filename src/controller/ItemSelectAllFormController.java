package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import view.Tm.ItemTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemSelectAllFormController {
    public TableView<ItemTm> tblItem;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn colUnitPrice;

    public void initialize() {
        try {

            colCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

            loadAllItems();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllItems() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item");
        ResultSet rst = stm.executeQuery();
        ArrayList<Item> Item = new ArrayList<>();
        while (rst.next()) {
            Item.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            ));
        }
        setItemsToTable(Item);
    }

    private void setItemsToTable(ArrayList<Item> customers) {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        customers.forEach(e->{
            obList.add(
                    new ItemTm(e.getItemCode(),e.getItemName(),e.getQtyOnHand(),e.getUnitPrice()));
        });
        tblItem.setItems(obList);
    }
}