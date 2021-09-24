package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import view.Tm.CartTm;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class BillFormController {
    public Label lblOrderId;
    public Label lblCusId;
    public Label lblDate;
    public Label lblTime;
    public Label lblItem;
    public Label lblTotal;

    public void initialize(){
        loadDateAndTime();
        //loadTotal();
        loadCustomerIds();
        loadOrderIds();
    }

    private void loadOrderIds() {
        try {
            lblOrderId.setText(new OrderController().getOrderId());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerIds() {
        try {
            lblCusId.setText(String.valueOf(new CustomerController().getCustomerIds()));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   /* private void loadTotal() {
        double ttl = 0;
        ttl=;
        lblTotal.setText(ttl + "/=");
    }*/

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

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
