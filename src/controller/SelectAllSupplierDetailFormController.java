package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.SupplierDetail;
import view.Tm.SupplierDetailTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectAllSupplierDetailFormController {
    public TableView <SupplierDetailTm>tblSupplierDetail;
    public TableColumn colSupId;
    public TableColumn colWoodTypeId;
    public TableColumn colQTY;
    public TableColumn colSize;

    public void initialize() {
        try {

            colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierOrderId"));
            colWoodTypeId.setCellValueFactory(new PropertyValueFactory<>("woodTypeId"));
            colQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
            colSize.setCellValueFactory(new PropertyValueFactory<>("Size"));

            loadAllSupplierDetails();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllSupplierDetails() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM SupplierDetail");
        ResultSet rst = stm.executeQuery();
        ArrayList<SupplierDetail> supplierDetails = new ArrayList<>();
        while (rst.next()) {
            supplierDetails.add(new SupplierDetail(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4)
            ));
        }
        setCustomersToTable(supplierDetails);
    }

    private void setCustomersToTable(ArrayList<SupplierDetail> customers) {
        ObservableList<SupplierDetailTm> obList = FXCollections.observableArrayList();
        customers.forEach(e->{
            obList.add(
                    new SupplierDetailTm(e.getSupplierOrderID(),e.getWoodTypeID(),e.getQTY(),e.getSize()));
        });
        tblSupplierDetail.setItems(obList);
    }
}