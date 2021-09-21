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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.java2d.pipe.SpanClipRenderer;

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
        loadDate();
        loadTime();
    }

    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd");
        lblDate.setText(f.format(date));
    }

    private void loadTime() {
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

    public void CustomerFormOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"))));
        stage.setTitle("Customer's Form");
        stage.show();
    }

    public void machineryOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MachineryForm.fxml"))));
        stage.setTitle("Machinery Form");
        stage.show();
    }

    public void reportOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReportForm.fxml"))));
        stage.setTitle("Report Form");
        stage.show();
    }

    public void itemOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"))));
        stage.setTitle("Item Form");
        stage.show();
    }

    public void ServantOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/EmployeeForm.fxml"))));
        stage.setTitle("Employee Form");
        stage.show();
    }

    public void supplierOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SupplierForm.fxml"))));
        stage.setTitle("Supplier Form");
        stage.show();
    }

    public void woodTypeOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/WoodTypeForm.fxml"))));
        stage.setTitle("Wood Type Form");
        stage.show();
    }

    public void placeOrderOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OrderForm.fxml"))));
        stage.setTitle("Place Order Form");
        stage.show();
    }

    public void expenseOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ExpenseForm.fxml"))));
        stage.setTitle("Expense Form");
        stage.show();
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        context.getChildren().clear();
        context.getChildren().add(load);
    }

    public void customerOnAction(ActionEvent actionEvent) {
    }

    public void orderOnAction(ActionEvent actionEvent) {
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
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AboutUs.fxml"))));
        stage.setTitle("About Us");
        stage.show();
    }

}