package util;

import com.sun.deploy.util.OrderedHashSet;
import db.DbConnection;
import javafx.collections.FXCollections;
import model.Order1;
import model.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseAccessCode {

    public ArrayList<Order1> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<Order1> list = new ArrayList<>();
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT c.CusID,c.CusName,o.OrderID,o.Date,o.Cost FROM Customer c JOIN Orders o ON o.CustomerID=c.CustomerID").executeQuery();
        while (rst.next()) {
            list.add(
              new Order1(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5)
            ));
        }
        return list;
    }

    public ArrayList<OrderDetail> getAllOrderDetails(String OrderId) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetail> details = new ArrayList<>();
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT itemCode,qty,unttPrice FROM OrderDetails WHERE OrderId=+'" + OrderId + "'").executeQuery();
        while (rst.next()) {
            details.add(
                   new OrderDetail(
                           rst.getString(1),
                           rst.getInt(2),
                           rst.getDouble(3)
                   )
            );
        }
        return details;
    }
}
