package model;

public class Customer {
    private String CusID;
    private String CusName;
    private String CusAddress;
    private String CusTelNo;

    public Customer() {
    }

    public Customer(String cusID, String cusName, String cusAddress, String cusTelNo) {
        CusID = cusID;
        CusName = cusName;
        CusAddress = cusAddress;
        CusTelNo = cusTelNo;
    }

    public String getCusID() {
        return CusID;
    }

    public void setCusID(String cusID) {
        CusID = cusID;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public String getCusAddress() {
        return CusAddress;
    }

    public void setCusAddress(String cusAddress) {
        CusAddress = cusAddress;
    }

    public String getCusTelNo() {
        return CusTelNo;
    }

    public void setCusTelNo(String cusTelNo) {
        CusTelNo = cusTelNo;
    }
}
