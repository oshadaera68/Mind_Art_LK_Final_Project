package model;

public class Expense {
    private String Id;
    private String Type;
    private double amount;

    public Expense(String id, String type, double amount) {
        Id = id;
        Type = type;
        this.amount = amount;
    }

    public Expense() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
