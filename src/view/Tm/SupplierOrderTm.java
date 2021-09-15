package view.Tm;

public class SupplierOrderTm {
    private String SupplierOrderID;
    private String SupplierOrderName;
    private String date;

    public SupplierOrderTm() {
    }

    public SupplierOrderTm(String supplierOrderID, String supplierOrderName, String date) {
        SupplierOrderID = supplierOrderID;
        SupplierOrderName = supplierOrderName;
        this.date = date;
    }

    public String getSupplierOrderID() {
        return SupplierOrderID;
    }

    public void setSupplierOrderID(String supplierOrderID) {
        SupplierOrderID = supplierOrderID;
    }

    public String getSupplierOrderName() {
        return SupplierOrderName;
    }

    public void setSupplierOrderName(String supplierOrderName) {
        SupplierOrderName = supplierOrderName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SupplierOrderTm{" +
                "SupplierOrderID='" + SupplierOrderID + '\'' +
                ", SupplierOrderName='" + SupplierOrderName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}