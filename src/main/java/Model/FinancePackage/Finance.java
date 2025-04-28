package Model.FinancePackage;

import Model.EventPackage.Event;

import java.util.List;

public class Finance {
    private String id;
    private Event event;
    private List<Expense> expenses;
    private List<Revenue> revenues;
    private double balance;

    public Finance(String id, Event event, List<Expense> expenses, List<Revenue> revenues) {
        this.id = id;
        this.event = event;
        this.expenses = expenses;
        this.revenues = revenues;
        this.balance = calculateBalance();
    }

    private double calculateBalance() {
        double totalRevenue = revenues.stream().mapToDouble(Revenue::getAmount).sum();
        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
        return totalRevenue - totalExpense;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public List<Expense> getExpenses() { return expenses; }
    public void setExpenses(List<Expense> expenses) { this.expenses = expenses; }

    public List<Revenue> getRevenues() { return revenues; }
    public void setRevenues(List<Revenue> revenues) { this.revenues = revenues; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
