package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.util.Duration;
import model.Customer;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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

    public void initialize() {
        loadDateAndTime();
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
    }

    private void setCustomerData(String CustomerId) throws SQLException, ClassNotFoundException {
        Customer c1 =new CustomerController().getCustomer(CustomerId);
        if (c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else{
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
}