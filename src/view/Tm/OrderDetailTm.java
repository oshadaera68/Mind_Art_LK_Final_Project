package view.Tm;

public class OrderDetailTm {
    private String itemCode;
    private int qty;
    private double UnitPrice;

    public OrderDetailTm() {
    }

    public OrderDetailTm(String itemCode, int qty, double unitPrice) {
        this.itemCode = itemCode;
        this.qty = qty;
        UnitPrice = unitPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailTm{" +
                "itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", UnitPrice=" + UnitPrice +
                '}';
    }
}