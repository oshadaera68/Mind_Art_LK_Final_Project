package model;

import java.util.Objects;

public class Item {
    private String ItemCode;
    private String ItemName;
    private int QtyOnHand;
    private double UnitPrice;

    public Item(String itemCode, String itemName, int qtyOnHand, double unitPrice) {
        this.ItemCode = itemCode;
        this.ItemName = itemName;
        this.QtyOnHand = qtyOnHand;
        this.UnitPrice = unitPrice;
    }

    public Item() {
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(ItemCode, item.ItemCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ItemCode);
    }
}