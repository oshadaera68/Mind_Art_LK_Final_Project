package view.Tm;

import java.util.Objects;

public class OrderTm {
    private String cusId;
    private String cusName;
    private String orderId;
    private String orderDate;
    private double totalCost;

    public OrderTm() {
    }

    public OrderTm(String cusId, String cusName, String orderId, String orderDate, double totalCost) {
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
        return "OrderTm{" +
                "cusId='" + getCusId() + '\'' +
                ", cusName='" + getCusName() + '\'' +
                ", orderId='" + getOrderId() + '\'' +
                ", orderDate='" + getOrderDate() + '\'' +
                ", totalCost=" + getTotalCost() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderTm orderTm = (OrderTm) o;
        return Double.compare(orderTm.totalCost, totalCost) == 0 && Objects.equals(cusId, orderTm.cusId) && Objects.equals(cusName, orderTm.cusName) && Objects.equals(orderId, orderTm.orderId) && Objects.equals(orderDate, orderTm.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cusId, cusName, orderId, orderDate, totalCost);
    }
}