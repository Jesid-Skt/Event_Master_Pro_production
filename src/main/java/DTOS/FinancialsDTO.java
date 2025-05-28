package DTOS;

public class FinancialsDTO {
    private String financialId;
    private String eventId;
    private Double income;   // Cambiado a Double
    private Double expense;  // Cambiado a Double
    private Double budget;   // Cambiado a Double

    public FinancialsDTO() {}

    public FinancialsDTO(String financialId, String eventId, Double income, Double expense, Double budget) {
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

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
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
