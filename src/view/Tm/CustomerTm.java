package view.Tm;

public class CustomerTm {
    private String CusID;
    private String CusName;
    private String CusAddress;
    private String CusTelNo;

    public CustomerTm() {
    }

    public CustomerTm(String cusID, String cusName, String cusAddress, String cusTelNo) {
        this.CusID = cusID;
        this.CusName = cusName;
        this.CusAddress = cusAddress;
        this.CusTelNo = cusTelNo;
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

    @Override
    public String toString() {
        return "CustomerTm{" +
                "CusID='" + CusID + '\'' +
                ", CusName='" + CusName + '\'' +
                ", CusAddress='" + CusAddress + '\'' +
                ", CusTelNo='" + CusTelNo + '\'' +
                '}';
    }
}