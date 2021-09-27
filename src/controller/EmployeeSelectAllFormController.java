package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import view.Tm.EmployeeTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeSelectAllFormController {
    public TableView <EmployeeTm>tblEmployee;
    public TableColumn colEmpID;
    public TableColumn colEmpName;
    public TableColumn colAddress;
    public TableColumn colTeleNo;

    public void initialize() {
        try {
            colEmpID.setStyle("-fx-alignment:CENTER;");
            colEmpName.setStyle("-fx-alignment:CENTER;");
            colAddress.setStyle("-fx-alignment:CENTER");
            colTeleNo.setStyle("-fx-alignment:CENTER");

            colEmpID.setCellValueFactory(new PropertyValueFactory<>("empId"));
            colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colTeleNo.setCellValueFactory(new PropertyValueFactory<>("teleNo"));

            loadAllEmployees();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllEmployees() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee");
        ResultSet rst = stm.executeQuery();
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        while (rst.next()) {
            employeeArrayList.add(new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        setEmployeesToTable(employeeArrayList);
    }

    private void setEmployeesToTable(ArrayList<Employee> customers) {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
        customers.forEach(e->{
            obList.add(
                    new EmployeeTm(e.getEmpId(),e.getEmpName(),e.getAddress(),e.getTeleNo()));
        });
        tblEmployee.setItems(obList);
    }
}