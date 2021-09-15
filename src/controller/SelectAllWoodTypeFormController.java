package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.WoodType;
import view.Tm.WoodTypeTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectAllWoodTypeFormController {
    public TableView <WoodTypeTm> tblWoodType;
    public TableColumn colId;
    public TableColumn colName;

    public void initialize() {
        try {

            colId.setCellValueFactory(new PropertyValueFactory<>("woodTypeID"));
            colName.setCellValueFactory(new PropertyValueFactory<>("woodName"));

            loadAllWoodTypes();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllWoodTypes() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM WoodType");
        ResultSet rst = stm.executeQuery();
        ArrayList<WoodType> woodTypeArrayList = new ArrayList<>();
        while (rst.next()) {
            woodTypeArrayList.add(new WoodType(
                    rst.getString(1),
                    rst.getString(2)
            ));
        }
        setWoodTypesToTable(woodTypeArrayList);
    }

    private void setWoodTypesToTable(ArrayList<WoodType> woodTypeArrayList) {
        ObservableList<WoodTypeTm> obList = FXCollections.observableArrayList();
        woodTypeArrayList.forEach(e->{
            obList.add(
                    new WoodTypeTm(e.getWoodTypeID(),e.getWoodName()));
        });
        tblWoodType.setItems(obList);
    }
}
