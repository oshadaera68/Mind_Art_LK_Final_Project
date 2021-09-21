package view.Tm;

public class ExpenseTm {
    private String id;
    private String type;
    private double amount;

    public ExpenseTm(String id, String type, double amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public ExpenseTm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
