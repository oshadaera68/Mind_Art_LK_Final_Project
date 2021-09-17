package controller;

import db.DbConnection;
import model.ItemDetails;
import model.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    public boolean placeOrder(Order order){
        try {
          PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Orders VALUES (?,?,?,?,?)");
          stm.setObject(1,order.getOrderID());
          stm.setObject(2,order.getDate());
          stm.setObject(3,order.getTime());
          stm.setObject(4,order.getCustomerId());
          stm.setObject(5,order.getCost());


          if (stm.executeUpdate()>0){
             return saveOrderDetail(order.getCustomerId(),order.getDetails());
          }else{
              return false;
          }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean saveOrderDetail(String orderId, ArrayList<ItemDetails> itemDetails) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp: itemDetails) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO OrderDetails VALUES (?,?,?)");
            stm.setObject(1,temp.getItemCode());
            stm.setObject(2,orderId);
            stm.setObject(3,temp.getQtyForSell());

            if (stm.executeUpdate()>0){

            }else{
                return false;
            }

        }
        return true;
    }
}