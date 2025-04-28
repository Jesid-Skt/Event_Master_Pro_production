package Services;


import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TicketService {


    private final HashMap<String, List<String>> ticketTypesByEvent = new HashMap<>();
    private final HashMap<String, Integer> ticketSalesByEvent = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public void createTicketType() {
        try {
            System.out.println("\n🎟️ Creating a new ticket type...");

            // Generar ID único automáticamente
            String eventId = UUID.randomUUID().toString().substring(0, 8);

            System.out.print("Enter Ticket Type Name (e.g., VIP, General, Backstage): ");
            String ticketType = scanner.nextLine();

            ticketTypesByEvent.putIfAbsent(eventId, new ArrayList<>());
            ticketTypesByEvent.get(eventId).add(ticketType);

            System.out.println("✅ Ticket type created successfully!");
            System.out.println("📌 Event ID: " + eventId);
            System.out.println("🎟️ Ticket Type: " + ticketType);
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void registerSale() {
        try {
            System.out.println("\n🎟️ Registering a ticket sale...");
            System.out.print("Enter Event ID: ");
            String eventId = scanner.nextLine();

            if (!ticketTypesByEvent.containsKey(eventId)) {
                System.out.println("❌ No ticket types found for this event.");
                return;
            }

            System.out.println("Available Ticket Types:");
            List<String> types = ticketTypesByEvent.get(eventId);
            for (int i = 0; i < types.size(); i++) {
                System.out.println((i + 1) + ". " + types.get(i));
            }

            System.out.print("Select Ticket Type by number: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > types.size()) {
                System.out.println("❌ Invalid choice.");
                return;
            }

            String selectedType = types.get(choice - 1);
            System.out.println("✅ Sale registered for ticket type: " + selectedType);

            ticketSalesByEvent.put(eventId, ticketSalesByEvent.getOrDefault(eventId, 0) + 1);
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Please enter a number.");
            scanner.nextLine(); // limpiar el buffer para que no quede basura
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public HashMap<String, List<String>> getTicketTypesByEvent() {
        return ticketTypesByEvent;
    }

    public HashMap<String, Integer> getTicketSalesByEvent() {
        return ticketSalesByEvent;
    }
}
