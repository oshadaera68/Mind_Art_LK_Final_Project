package view.Tm;

public class SupplierDetailTm {
    private String SupplierOrderID;
    private String WoodTypeID;
    private int QTY;
    private int Size;

    public SupplierDetailTm() {
    }

    public SupplierDetailTm(String supplierOrderID, String woodTypeID, int QTY, int size) {
        SupplierOrderID = supplierOrderID;
        WoodTypeID = woodTypeID;
        this.QTY = QTY;
        Size = size;
    }

    public String getSupplierOrderID() {
        return SupplierOrderID;
    }

    public void setSupplierOrderID(String supplierOrderID) {
        SupplierOrderID = supplierOrderID;
    }

    public String getWoodTypeID() {
        return WoodTypeID;
    }

    public void setWoodTypeID(String woodTypeID) {
        WoodTypeID = woodTypeID;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public int getSize() {
        return Size;
    }

    @Override
    public String toString() {
        return "SupplierDetailTm{" +
                "SupplierOrderID='" + SupplierOrderID + '\'' +
                ", WoodTypeID='" + WoodTypeID + '\'' +
                ", QTY=" + QTY +
                ", Size=" + Size +
                '}';
    }
}