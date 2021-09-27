package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Supplier;
import view.Tm.SupplierTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierSelectAllFormController {
    public TableView<SupplierTm> tblSupplier;
    public TableColumn colSupplierID;
    public TableColumn colSupplierName;
    public TableColumn colSupplierAddress;
    public TableColumn colTelNo;

    public void initialize() {
        try {

            colSupplierID.setStyle("-fx-alignment:CENTER;");
            colSupplierName.setStyle("-fx-alignment:CENTER;");
            colSupplierAddress.setStyle("-fx-alignment:CENTER");
            colTelNo.setStyle("-fx-alignment:CENTER");

            colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
            colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
            colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
            colTelNo.setCellValueFactory(new PropertyValueFactory<>("telNo"));

            loadAllSuppliers();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllSuppliers() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier");
        ResultSet rst = stm.executeQuery();
        ArrayList<Supplier> suppliers = new ArrayList();
        while (rst.next()) {
            suppliers.add(new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        setSuppliersToTable(suppliers);
    }

    private void setSuppliersToTable(ArrayList<Supplier> supplierArrayList) {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();
        supplierArrayList.forEach(e -> {
            obList.add(
                    new SupplierTm(e.getSupplierID(), e.getSupplierName(), e.getSupplierAddress(), e.getTelNo()));
        });
        tblSupplier.setItems(obList);
    }
}