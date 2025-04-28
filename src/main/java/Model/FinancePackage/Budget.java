package Model.FinancePackage;

import java.util.List;

public class Budget {
    private double totalBudget;
    private List<Expense> expenses;

    public Budget(double totalBudget, List<Expense> expenses) {
        this.totalBudget = totalBudget;
        this.expenses = expenses;
    }

    public double getTotalBudget() { return totalBudget; }
    public void setTotalBudget(double totalBudget) { this.totalBudget = totalBudget; }

    public List<Expense> getExpenses() { return expenses; }
    public void setExpenses(List<Expense> expenses) { this.expenses = expenses; }
}
