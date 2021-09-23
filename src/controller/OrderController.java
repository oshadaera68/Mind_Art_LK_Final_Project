package controller;

import db.DbConnection;
import model.ItemDetails;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {

    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT OrderID FROM orders ORDER BY OrderID DESC LIMIT 1 ").executeQuery();
        if (rst.next()){
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId+1;
            if (tempId<9){
                return "O-00"+tempId;
            }else if (tempId<99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }
        }else{
            return "O-001";
        }
    }

    public boolean placeOrder(Order order){
        Connection con = null;
        try {
           con = DbConnection.getInstance().getConnection();
          con.setAutoCommit(false);
          PreparedStatement stm = con.prepareStatement("INSERT INTO Orders() VALUES (?,?,?,?,?)");
          stm.setObject(1,order.getOrderID());
          stm.setObject(2,order.getCustomerId());
          stm.setObject(3,order.getDate());
          stm.setObject(4,order.getTime());
          stm.setObject(5,order.getCost());

          if (stm.executeUpdate()>0){
             if(saveOrderDetail(order.getOrderID(),order.getDetails())){
                 con.commit();
                 return true;
              }
          }else{
              con.rollback();
              return false;
          }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean saveOrderDetail(String orderId, ArrayList<ItemDetails> itemDetails) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp: itemDetails) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO OrderDetails(ItemCode,OrderID,Qty,price) VALUES (?,?,?,?)");
            stm.setObject(1,temp.getItemCode());
            stm.setObject(2,orderId);
            stm.setObject(3,temp.getQtyForSell());

            if (stm.executeUpdate()>0){
                if (updateQty(temp.getItemCode(), temp.getQtyForSell())) {

                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("UPDATE item SET qtyOnHand=(qtyOnHand-"+qty+")WHERE ItemCode='"+itemCode+"'");
        return preparedStatement.executeUpdate()>0;
    }
}