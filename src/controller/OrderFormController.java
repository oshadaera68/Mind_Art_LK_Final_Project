package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.ItemDetails;
import model.Order;
import view.Tm.CartTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderFormController {

    public Label lblDate;
    public Label lblTime;
    public JFXComboBox<String> cmbCustomerIds;
    public JFXComboBox<String> cmbItemIds;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusTelNo;
    public JFXTextField txtItemName;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public TableView<CartTm> tblCart;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public JFXTextField txtQty;
    public Label txtTtl;
    public Label lblOrderId;

    int cartSelectedRowForRemove = -1;

    public void initialize() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();
        setOrderId();
        try {

            loadCustomerIds();
            loadItemIds();

        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData(newValue);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbItemIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });
    }

    private void setOrderId() {
        try {
            lblOrderId.setText(new OrderController().getOrderId());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtItemName.setText(i1.getItemName());
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
        }
    }

    private void setCustomerData(String CustomerId) throws SQLException, ClassNotFoundException {
        Customer c1 = new CustomerController().getCustomer(CustomerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtCusName.setText(c1.getCusName());
            txtCusAddress.setText(c1.getCusAddress());
            txtCusTelNo.setText(c1.getCusTelNo());
        }
    }

    private void loadItemIds() throws Exception {
        List<String> itemIds = new ItemController().getAllItemCodes();
        cmbItemIds.getItems().addAll(itemIds);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomerIds.getItems().addAll(customerIds);
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        lblDate.setText(format.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime localTime = LocalTime.now();
            lblTime.setText(
                    localTime.getHour() + " : " + localTime.getMinute() + " : " + localTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    ObservableList<CartTm> observableList = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {
        String itemName = txtItemName.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double price = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = qty * price;

        if (qtyOnHand < qty) {
            new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
            return;
        }

        CartTm cartTm = new CartTm(
                cmbItemIds.getValue(),
                itemName,
                qty,
                price,
                total
        );

        int rowNumber = isExists(cartTm);
        if (rowNumber == -1) {
            observableList.add(cartTm);
        } else {
            CartTm temp = observableList.get(rowNumber);
            CartTm newTm = new CartTm(
                    temp.getCode(),
                    temp.getName(),
                    temp.getQty() + qtyOnHand,
                    price,
                    total + temp.getTotal()
            );
            if (qtyOnHand < temp.getQty()) {
                new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
                return;
            }
            observableList.remove(rowNumber);
            observableList.add(newTm);
        }
        tblCart.setItems(observableList);
        CalculateCost();
    }

    private int isExists(CartTm tm) {
        for (int i = 0; i < observableList.size(); i++) {
            if (tm.getCode().equals(observableList.get(i).getCode())) {
                return i;
            }
        }
        return -1;
    }

    void CalculateCost() {
        double ttl = 0;
        for (CartTm tm : observableList) {
            ttl += tm.getTotal();
        }
        txtTtl.setText(ttl + "/=");
    }

    public void clearOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "please select a row").show();
        } else {
            observableList.remove(cartSelectedRowForRemove);
            tblCart.refresh();
        }
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        ArrayList<ItemDetails> itemDetails = new ArrayList<>();
        double ttl=0;
        for (CartTm tempTm : observableList) {
            ttl+=tempTm.getTotal();
            itemDetails.add(new ItemDetails(tempTm.getCode(),tempTm.getUnitPrice(), tempTm.getQty()));
        }
        Order order = new Order(
                lblOrderId.getText(),cmbCustomerIds.getValue(),lblDate.getText(),lblTime.getText(),ttl,itemDetails
        );
        if (new OrderController().placeOrder(order)){
            URL resource = getClass().getResource("../view/BillForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Bill");
            stage.show();
//            new Alert(Alert.AlertType.CONFIRMATION,"Success").show();
            setOrderId();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }
}