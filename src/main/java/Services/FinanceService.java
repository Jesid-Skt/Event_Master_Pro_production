package Services;

            import DTOS.FinancialsDTO;
            import Repository.FinancialRepository;

            import java.math.BigDecimal;
            import java.util.HashMap;
            import java.util.List;
            import java.util.Map;

            public class FinanceService {

                private final FinancialRepository repository = new FinancialRepository();

                public FinanceService() {
                    repository.loadFromFile();
                }

                public boolean registerFinancials(String eventId, BigDecimal budget, BigDecimal income, BigDecimal expense) {
                    if (eventId == null || eventId.isEmpty()) {
                        throw new IllegalArgumentException("El ID del evento no puede ser nulo o vacío.");
                    }
                    if ((budget == null || budget.compareTo(BigDecimal.ZERO) < 0) &&
                        (income == null || income.compareTo(BigDecimal.ZERO) < 0) &&
                        (expense == null || expense.compareTo(BigDecimal.ZERO) < 0)) {
                        throw new IllegalArgumentException("Debe ingresar al menos un valor positivo.");
                    }

                    FinancialsDTO dto = new FinancialsDTO();
                    dto.setFinancialId(java.util.UUID.randomUUID().toString());
                    dto.setEventId(eventId);
                    dto.setBudget(budget != null ? budget.doubleValue() : 0.0);
                    dto.setIncome(income != null ? income.doubleValue() : 0.0);
                    dto.setExpense(expense != null ? expense.doubleValue() : 0.0);

                    repository.addFinancial(dto);
                    return true;
                }

                public FinancialsDTO getFinancialById(String financialId) {
                    return repository.getById(financialId);
                }

                public List<FinancialsDTO> getFinancialsByEventId(String eventId) {
                    return repository.getByEventId(eventId);
                }

                public List<FinancialsDTO> getAllFinancials() {
                    return repository.getAllFinancials();
                }

                public String getFinancialSummary() {
                    List<FinancialsDTO> all = repository.getAllFinancials();
                    if (all.isEmpty()) return "❌ No financial records found.";

                    StringBuilder sb = new StringBuilder("📈 Financial Summary for All Events\n");
                    double totalIncome = 0;
                    double totalExpenses = 0;
                    Map<String, Double> balances = new HashMap<>();

                    for (FinancialsDTO dto : all) {
                        double balance = dto.getIncome() - dto.getExpense();
                        balances.put(dto.getEventId(), balances.getOrDefault(dto.getEventId(), 0.0) + balance);
                        totalIncome += dto.getIncome();
                        totalExpenses += dto.getExpense();
                    }

                    for (String eventId : balances.keySet()) {
                        sb.append("\n🎯 Event ID: ").append(eventId)
                          .append("\nCurrent Balance: $").append(balances.get(eventId));
                    }

                    sb.append("\n\n=== Overall Summary ===")
                      .append("\nTotal Income: $").append(totalIncome)
                      .append("\nTotal Expenses: $").append(totalExpenses)
                      .append("\nNet Balance: $").append(totalIncome - totalExpenses);

                    return sb.toString();
                }
            }