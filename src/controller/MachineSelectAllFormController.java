package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Machine;
import view.Tm.MachineTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MachineSelectAllFormController {
    public TableView <MachineTm>tblMachine;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn colModel;

    public void initialize() {
        try {

            colId.setStyle("-fx-alignment:CENTER;");
            colName.setStyle("-fx-alignment:CENTER;");
            colQty.setStyle("-fx-alignment:CENTER");
            colModel.setStyle("-fx-alignment:CENTER");

            colId.setCellValueFactory(new PropertyValueFactory<>("machineID"));
            colName.setCellValueFactory(new PropertyValueFactory<>("machineName"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("machineQty"));
            colModel.setCellValueFactory(new PropertyValueFactory<>("model"));

            loadAllMachines();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllMachines() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        ArrayList<Machine> machines = new ArrayList<>();
        while (rst.next()) {
            machines.add(new Machine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4)
            ));
        }
        setMachinesToTable(machines);
    }

    private void setMachinesToTable(ArrayList<Machine> customers) {
        ObservableList<MachineTm> obList = FXCollections.observableArrayList();
        customers.forEach(e->{
            obList.add(
                    new MachineTm(e.getMachineID(),e.getMachineName(),e.getMachineQty(),e.getModel()));
        });
        tblMachine.setItems(obList);
    }
}