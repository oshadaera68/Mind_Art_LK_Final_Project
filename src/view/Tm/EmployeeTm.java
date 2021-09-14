package view.Tm;

public class EmployeeTm {
    private String EmpId;
    private String EmpName;
    private String Address;
    private String TeleNo;

    public EmployeeTm() {
    }

    public EmployeeTm(String empId, String empName, String address, String teleNo) {
        this.EmpId = empId;
        this.EmpName = empName;
        this.Address = address;
        this.TeleNo = teleNo;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTeleNo() {
        return TeleNo;
    }

    public void setTeleNo(String teleNo) {
        TeleNo = teleNo;
    }

    @Override
    public String toString() {
        return "EmployeeTm{" +
                "EmpId='" + EmpId + '\'' +
                ", EmpName='" + EmpName + '\'' +
                ", Address='" + Address + '\'' +
                ", TeleNo='" + TeleNo + '\'' +
                '}';
    }
}