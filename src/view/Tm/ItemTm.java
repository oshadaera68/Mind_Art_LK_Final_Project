package view.Tm;

public class ItemTm {
    private String ItemCode;
    private String ItemName;
    private int QtyOnHand;
    private double UnitPrice;

    public ItemTm(String itemCode, String itemName, int qtyOnHand, double unitPrice) {
        this.ItemCode = itemCode;
        this.ItemName = itemName;
        this.QtyOnHand = qtyOnHand;
        this.UnitPrice = unitPrice;
    }

    public ItemTm() {
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
    public String toString() {
        return "ItemTm{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", QtyOnHand=" + QtyOnHand +
                ", UnitPrice=" + UnitPrice +
                '}';
    }
}

