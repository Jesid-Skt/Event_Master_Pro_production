package Services;

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FinanceService {

    private final Map<String, BigDecimal> finances = new HashMap<>();
    private final Map<String, List<BigDecimal>> incomeHistory = new HashMap<>();
    private final Map<String, List<BigDecimal>> expenseHistory = new HashMap<>();

    public String registerBudget(BigDecimal budgetAmount) {
        if (budgetAmount == null || budgetAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Budget must be a positive number.");
        }

        String eventId = UUID.randomUUID().toString().substring(0, 8);
        finances.put(eventId, budgetAmount.setScale(2, RoundingMode.HALF_UP));
        return eventId;
    }

    public boolean trackIncome(String eventId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) return false;
        finances.putIfAbsent(eventId, BigDecimal.ZERO);
        finances.put(eventId, finances.get(eventId).add(amount.setScale(2, RoundingMode.HALF_UP)));
        incomeHistory.computeIfAbsent(eventId, k -> new ArrayList<>()).add(amount);
        return true;
    }

    public boolean trackExpense(String eventId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) return false;
        finances.putIfAbsent(eventId, BigDecimal.ZERO);
        finances.put(eventId, finances.get(eventId).subtract(amount.setScale(2, RoundingMode.HALF_UP)));
        expenseHistory.computeIfAbsent(eventId, k -> new ArrayList<>()).add(amount);
        return true;
    }

    public List<BigDecimal> getIncomeHistory(String eventId) {
        return incomeHistory.getOrDefault(eventId, Collections.emptyList());
    }

    public List<BigDecimal> getExpenseHistory(String eventId) {
        return expenseHistory.getOrDefault(eventId, Collections.emptyList());
    }

    public BigDecimal getCurrentBalance(String eventId) {
        return finances.getOrDefault(eventId, BigDecimal.ZERO);
    }

    public Map<String, BigDecimal> getFinances() {
        return Collections.unmodifiableMap(finances);
    }

    public Map<String, List<BigDecimal>> getIncomeHistories() {
        return Collections.unmodifiableMap(incomeHistory);
    }

    public Map<String, List<BigDecimal>> getExpenseHistories() {
        return Collections.unmodifiableMap(expenseHistory);
    }

    public String getFinancialSummary() {
        if (finances.isEmpty()) return "❌ No financial records found.";

        StringBuilder sb = new StringBuilder("📈 Financial Summary for All Events\n");
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpenses = BigDecimal.ZERO;

        for (String eventId : finances.keySet()) {
            sb.append("\n🎯 Event ID: ").append(eventId)
                    .append("\nCurrent Balance: $").append(finances.get(eventId));

            if (incomeHistory.containsKey(eventId)) {
                BigDecimal eventIncome = incomeHistory.get(eventId).stream()
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                sb.append("\nTotal Income: $").append(eventIncome);
                totalIncome = totalIncome.add(eventIncome);
            }

            if (expenseHistory.containsKey(eventId)) {
                BigDecimal eventExpenses = expenseHistory.get(eventId).stream()
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                sb.append("\nTotal Expenses: $").append(eventExpenses);
                totalExpenses = totalExpenses.add(eventExpenses);
            }
        }

        sb.append("\n\n=== Overall Summary ===")
                .append("\nTotal Income: $").append(totalIncome)
                .append("\nTotal Expenses: $").append(totalExpenses)
                .append("\nNet Balance: $").append(totalIncome.subtract(totalExpenses));

        return sb.toString();
    }
}

