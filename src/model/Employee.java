package model;

public class Employee {
    private String EmpId;
    private String EmpName;
    private String Address;
    private String TeleNo;

    public Employee() {
    }

    public Employee(String empId, String empName, String address, String teleNo) {
        EmpId = empId;
        EmpName = empName;
        Address = address;
        TeleNo = teleNo;
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
}
