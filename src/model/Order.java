package model;

import java.util.ArrayList;

public class Order {
   private String OrderID;
   private String customerID;
   private String Date;
   private String Time;
   private double cost;
   private ArrayList<ItemDetails> details;

   public Order() {
   }

   public Order(String orderID, String customerId, String date, String time, double cost, ArrayList<ItemDetails> details) {
      this.OrderID = orderID;
      this.customerID = customerId;
      this.Date = date;
      this.Time = time;
      this.cost = cost;
      this.details = details;
   }

   public String getOrderID() {
      return OrderID;
   }

   public void setOrderID(String orderID) {
      OrderID = orderID;
   }

   public String getCustomerId() {
      return customerID;
   }

   public void setCustomerId(String customerId) {
      this.customerID = customerId;
   }

   public String getDate() {
      return Date;
   }

   public void setDate(String date) {
      Date = date;
   }

   public String getTime() {
      return Time;
   }

   public void setTime(String time) {
      Time = time;
   }

   public double getCost() {
      return cost;
   }

   public void setCost(double cost) {
      this.cost = cost;
   }

   public ArrayList<ItemDetails> getDetails() {
      return details;
   }

   public void setDetails(ArrayList<ItemDetails> details) {
      this.details = details;
   }

   @Override
   public String toString() {
      return "Order{" +
              "OrderID='" + OrderID + '\'' +
              ", customerId='" + customerID + '\'' +
              ", Date='" + Date + '\'' +
              ", Time='" + Time + '\'' +
              ", cost=" + cost +
              ", details=" + details +
              '}';
   }
}