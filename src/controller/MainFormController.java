package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

public class MainFormController {
    public Label lblDate;
    public Label lblTime;
    public StackPane context;

    public void initialize() {
        loadDateAndTime();
    }

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

    public void machineryOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("MachineryForm");
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SupplierForm");
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("OrderForm");
    }

    public void expenseOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ExpenseForm");
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DashBoardForm");
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("CustomerForm");
    }

    public void logOutOnAction(ActionEvent actionEvent) {
        ButtonType buttonType = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonType1 = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Did you exit the system?", buttonType, buttonType1);
        alert.setTitle("Confirmation");
        Optional<ButtonType> close = alert.showAndWait();
        if (close.orElse(buttonType1) == buttonType) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void aboutUsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AboutUsForm.fxml"))));
        stage.setTitle("About Us");
        stage.show();
    }

    public void itemsOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ItemForm");
    }

    public void employeeOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("EmployeeForm");
    }

    public void woodTypesOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("WoodTypeForm");
    }

    public void reportsOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ReportForm");
    }

    void loadUi(String fileName) throws IOException {
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }
}