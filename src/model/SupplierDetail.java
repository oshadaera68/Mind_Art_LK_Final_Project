package model;

public class SupplierDetail {
    private String SupplierOrderID;
    private String WoodTypeID;
    private int QTY;
    private double Size;

    public SupplierDetail() {
    }

    public SupplierDetail(String supplierOrderID, String woodTypeID, int QTY, double size) {
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

    public double getSize() {
        return Size;
    }

    public void setSize(double size) {
        Size = size;
    }
}

