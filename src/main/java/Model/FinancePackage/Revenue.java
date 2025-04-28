package Model.FinancePackage;

public class Revenue {
    private String id;
    private String source;
    private double amount;

    public Revenue(String id, String source, double amount) {
        this.id = id;
        this.source = source;
        this.amount = amount;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

}
