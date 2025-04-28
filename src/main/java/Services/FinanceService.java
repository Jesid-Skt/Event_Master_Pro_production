package Services;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;

public class FinanceService {

    private final Map<String, BigDecimal> finances = new HashMap<>();
        private final Map<String, List<BigDecimal>> incomeHistory = new HashMap<>();
        private final Map<String, List<BigDecimal>> expenseHistory = new HashMap<>();
        private final Scanner scanner = new Scanner(System.in);

    public void registerBudget() {
        try {
            System.out.println("\n💵 Registering budget...");
            String eventId = UUID.randomUUID().toString().substring(0, 8);

            System.out.print("Enter Budget Amount: ");
            BigDecimal budget = new BigDecimal(scanner.nextLine())
                .setScale(2, RoundingMode.HALF_UP);

            finances.put(eventId, budget);
            System.out.println("✅ Budget registered successfully!");
            System.out.println("📌 Budget ID: " + eventId);
            System.out.println("💰 Amount: $" + budget.toString());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid budget amount. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

   public void trackIncome() {
        try {
            System.out.println("\n💵 Tracking income...");
            System.out.print("Enter Event ID: ");
            String eventId = scanner.nextLine();

            System.out.print("Enter Income Amount: ");
            BigDecimal income = new BigDecimal(scanner.nextLine())
                .setScale(2, RoundingMode.HALF_UP);

            finances.putIfAbsent(eventId, BigDecimal.ZERO);
            finances.put(eventId, finances.get(eventId).add(income));

            // Actualizar historial de ingresos
            incomeHistory.computeIfAbsent(eventId, k -> new ArrayList<>()).add(income);

            System.out.println("✅ Income added successfully!");
            System.out.println("📌 Event ID: " + eventId);
            System.out.println("💰 Amount: $" + income);
            System.out.println("Current Balance: $" + finances.get(eventId));
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid income amount. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

   public void trackExpense() {
        try {
            System.out.println("\n💵 Tracking expense...");
            System.out.print("Enter Event ID: ");
            String eventId = scanner.nextLine();

            System.out.print("Enter Expense Amount: ");
            BigDecimal expense = new BigDecimal(scanner.nextLine())
                .setScale(2, RoundingMode.HALF_UP);

            finances.putIfAbsent(eventId, BigDecimal.ZERO);
            finances.put(eventId, finances.get(eventId).subtract(expense));

            // Actualizar historial de gastos
            expenseHistory.computeIfAbsent(eventId, k -> new ArrayList<>()).add(expense);

            System.out.println("✅ Expense deducted successfully!");
            System.out.println("📌 Event ID: " + eventId);
            System.out.println("💸 Amount: $" + expense);
            System.out.println("Current Balance: $" + finances.get(eventId));
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid expense amount. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void viewIncome() {
        try {
            System.out.println("\n📊 Income Report");
            System.out.print("Enter Event ID: ");
            String eventId = scanner.nextLine();

            if (!incomeHistory.containsKey(eventId)) {
                System.out.println("❌ No income records found for this event.");
                return;
            }

            System.out.println("\n💰 Income Records for Event " + eventId + ":");
            BigDecimal totalIncome = BigDecimal.ZERO;
            for (BigDecimal amount : incomeHistory.get(eventId)) {
                System.out.println("$" + amount);
                totalIncome = totalIncome.add(amount);
            }
            System.out.println("\nTotal Income: $" + totalIncome);
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void viewExpenses() {
        try {
            System.out.println("\n📊 Expense Report");
            System.out.print("Enter Event ID: ");
            String eventId = scanner.nextLine();

            if (!expenseHistory.containsKey(eventId)) {
                System.out.println("❌ No expense records found for this event.");
                return;
            }

            System.out.println("\n💸 Expense Records for Event " + eventId + ":");
            BigDecimal totalExpenses = BigDecimal.ZERO;
            for (BigDecimal amount : expenseHistory.get(eventId)) {
                System.out.println("$" + amount);
                totalExpenses = totalExpenses.add(amount);
            }
            System.out.println("\nTotal Expenses: $" + totalExpenses);
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void viewFinancialSummary() {
        try {
            System.out.println("\n📈 Financial Summary for All Events");
            if (finances.isEmpty()) {
                System.out.println("❌ No financial records found.");
                return;
            }

            BigDecimal totalIncome = BigDecimal.ZERO;
            BigDecimal totalExpenses = BigDecimal.ZERO;

            System.out.println("\n=== Detailed Financial Report ===");
            for (String eventId : finances.keySet()) {
                System.out.println("\n🎯 Event ID: " + eventId);
                System.out.println("Current Balance: $" + finances.get(eventId));

                // Mostrar ingresos si existen
                if (incomeHistory.containsKey(eventId)) {
                    BigDecimal eventIncome = incomeHistory.get(eventId).stream()
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    totalIncome = totalIncome.add(eventIncome);
                    System.out.println("Total Income: $" + eventIncome);
                }

                // Mostrar gastos si existen
                if (expenseHistory.containsKey(eventId)) {
                    BigDecimal eventExpenses = expenseHistory.get(eventId).stream()
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    totalExpenses = totalExpenses.add(eventExpenses);
                    System.out.println("Total Expenses: $" + eventExpenses);
                }
            }

            System.out.println("\n=== Overall Summary ===");
            System.out.println("Total Income (all events): $" + totalIncome);
            System.out.println("Total Expenses (all events): $" + totalExpenses);
            System.out.println("Net Balance: $" + totalIncome.subtract(totalExpenses));
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public Map<String, BigDecimal> getFinances() {return finances;}

}

