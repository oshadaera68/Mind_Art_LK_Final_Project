package model;

import java.util.Date;

public class EmployeePayment {
    private String Id;
    private String Date;
    private double Amount;

    public EmployeePayment() {
    }

    public EmployeePayment(String id, String date, double amount) {
        Id = id;
        Date = date;
        Amount = amount;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }
}
