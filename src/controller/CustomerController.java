package controller;

import db.DbConnection;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService {
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String>ids = new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }

    @Override
    public boolean addCustomer(Customer c) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Customer VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getCusID());
        stm.setObject(2,c.getCusName());
        stm.setObject(3,c.getCusAddress());
        stm.setObject(4,c.getCusTelNo());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE CusID=?");
        stm.setObject(1, id);

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        } else {
          return null;
        }
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        return null;
    }
}