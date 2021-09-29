package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Order1;
import util.DatabaseAccessCode;
import view.Tm.OrderTm;

import java.io.IOException;
import java.sql.SQLException;

public class PlaceOrderController {
    public TableView <OrderTm>tblCusOrder;
    public TableColumn colCusID;
    public TableColumn colCusName;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colTotalCost;

    public void initialize(){

        colCusID.setStyle("-fx-alignment:CENTER;");
        colCusName.setStyle("-fx-alignment:CENTER;");
        colOrderId.setStyle("-fx-alignment:CENTER");
        colOrderDate.setStyle("-fx-alignment:CENTER");
        colTotalCost.setStyle("-fx-alignment:CENTER");

        colCusID.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        try {

            loadAllData();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblCusOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadDetailsUi(newValue.getOrderId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadDetailsUi(String orderId) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ItemDetailsForm.fxml"));
        Parent load = loader.load();
        ItemDetailsFormController controller = loader.getController();
        controller.loadAllData(orderId);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    private void loadAllData() throws SQLException, ClassNotFoundException {
        ObservableList <OrderTm> list = FXCollections.observableArrayList();
        for (Order1 tempOrder:new DatabaseAccessCode().getAllOrders()) {
            list.add(new OrderTm(tempOrder.getCusId(), tempOrder.getCusName(),tempOrder.getOrderId(), tempOrder.getOrderDate(), tempOrder.getTotalCost()));
        }
    }
}