package model;

public class Supplier {
    private String SupplierID;
    private String SupplierName;
    private String SupplierAddress;
    private String TelNo;

    public Supplier() {
    }

    public Supplier(String supplierID, String supplierName, String supplierAddress, String telNo) {
        SupplierID = supplierID;
        SupplierName = supplierName;
        SupplierAddress = supplierAddress;
        TelNo = telNo;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String supplierID) {
        SupplierID = supplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    public String getSupplierAddress() {
        return SupplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        SupplierAddress = supplierAddress;
    }

    public String getTelNo() {
        return TelNo;
    }

    public void setTelNo(String telNo) {
        TelNo = telNo;
    }
}
