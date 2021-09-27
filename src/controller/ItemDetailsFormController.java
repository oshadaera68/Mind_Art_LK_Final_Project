package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDetail;
import util.DatabaseAccessCode;
import view.Tm.OrderDetailTm;

import java.sql.SQLException;

public class ItemDetailsFormController {
    public TableView<OrderDetailTm> tblOrderDetails;
    public TableColumn colItemCode;
    public TableColumn colQTY;
    public TableColumn colPrice;
    public Label lblOrderId;
    public Label lblTotalCost;

    public void initialize() {

        colItemCode.setStyle("-fx-alignment:CENTER;");
        colQTY.setStyle("-fx-alignment:CENTER;");
        colPrice.setStyle("-fx-alignment:CENTER");

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
    }

    public void loadAllData(String id) {
        lblOrderId.setText(id);
        double total = 0;

        try {

            ObservableList<OrderDetailTm> objects = FXCollections.observableArrayList();
            for (OrderDetail tempData : new DatabaseAccessCode().getAllOrderDetails(id)
            ) {
                total+=tempData.getUnitPrice();
                objects.add(new OrderDetailTm(
                        tempData.getItemCode(),
                        tempData.getQty(),
                        tempData.getUnitPrice()
                ));
                tblOrderDetails.setItems(objects);
                lblTotalCost.setText("Rs"+total+"/=");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}