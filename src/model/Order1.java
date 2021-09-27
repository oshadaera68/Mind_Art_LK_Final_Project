package model;

import java.util.Objects;

public class Order1 {
    private String cusId;
    private String cusName;
    private String orderId;
    private String orderDate;
    private double totalCost;

    public Order1() {
    }

    public Order1(String cusId, String cusName, String orderId, String orderDate, double totalCost) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalCost = totalCost;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Order1{" +
                "cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order1 order1 = (Order1) o;
        return Double.compare(order1.totalCost, totalCost) == 0 && Objects.equals(cusId, order1.cusId) && Objects.equals(cusName, order1.cusName) && Objects.equals(orderId, order1.orderId) && Objects.equals(orderDate, order1.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cusId, cusName, orderId, orderDate, totalCost);
    }
}