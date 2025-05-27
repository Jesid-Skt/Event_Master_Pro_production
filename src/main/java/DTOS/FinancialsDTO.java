package DTOS;

public class FinancialsDTO {
    private String financialId;
    private String eventId;
    private double income;
    private double expense;
    private double budget;

    public FinancialsDTO() {}

    public FinancialsDTO(String financialId, String eventId, double income, double expense, double budget) {
        this.financialId = financialId;
        this.eventId = eventId;
        this.income = income;
        this.expense = expense;
        this.budget = budget;
    }

    public String getFinancialId() {
        return financialId;
    }

    public void setFinancialId(String financialId) {
        this.financialId = financialId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "FinancialsDTO{" +
                "financialId='" + financialId + '\'' +
                ", eventId='" + eventId + '\'' +
                ", income=" + income +
                ", expense=" + expense +
                ", budget=" + budget +
                '}';
    }
}