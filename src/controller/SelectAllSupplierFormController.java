package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.SupplierOrder;
import view.Tm.SupplierOrderTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectAllSupplierFormController {
    public TableView <SupplierOrderTm>tblOrder;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colDate;

    public void initialize() {
        try {

            colID.setCellValueFactory(new PropertyValueFactory<>("supplierOrderID"));
            colName.setCellValueFactory(new PropertyValueFactory<>("supplierOrderName"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("supplierOrderDate"));

            loadAllSupplierOrders();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllSupplierOrders() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM SupplierOrder");
        ResultSet rst = stm.executeQuery();
        ArrayList<SupplierOrder> supplierOrders = new ArrayList<>();
        while (rst.next()) {
            supplierOrders.add(new SupplierOrder(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }
        setCustomersToTable(supplierOrders);
    }

    private void setCustomersToTable(ArrayList<SupplierOrder> customers) {
        ObservableList<SupplierOrderTm> obList = FXCollections.observableArrayList();
        customers.forEach(e->{
            obList.add(
                    new SupplierOrderTm(e.getSupplierOrderID(),e.getSupplierOrderName(),e.getDate()));
        });
        tblOrder.setItems(obList);
    }
}