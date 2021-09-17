package view.Tm;

public class CartTm {
    private String code;
    private String Name;
    private int Qty;
    private double unitPrice;
    private double Total;

    public CartTm() {
    }

    public CartTm(String code, String name, int qty, double unitPrice, double total) {
        this.code = code;
        Name = name;
        Qty = qty;
        this.unitPrice = unitPrice;
        Total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    @Override
    public String toString() {
        return "CartTm{" +
                "code='" + code + '\'' +
                ", Name='" + Name + '\'' +
                ", Qty=" + Qty +
                ", unitPrice=" + unitPrice +
                ", Total=" + Total +
                '}';
    }
}