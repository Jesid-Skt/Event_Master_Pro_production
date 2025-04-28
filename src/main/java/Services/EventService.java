package Services;

import Model.EventPackage.Event;
import Model.EventPackage.Venue;
import Enums.EventType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class EventService {

    private final Map<String, Event> events = new HashMap<>();
    private final Map<String, Double> budgets = new HashMap<>();
    private final Map<String, List<String>> ticketSales = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private final VenueService venueService;

    public EventService(VenueService venueService) {
        this.venueService = venueService;
    }
    public void createEvent() {
        try {
            System.out.println("\n🔹 Creating a new event...");

            // Generar ID único automáticamente
            String id = UUID.randomUUID().toString().substring(0, 8);

            System.out.print("Enter Event Name: ");
            String name = scanner.nextLine();

            System.out.println("Select Event Type:");
            for (EventType type : EventType.values()) {
                System.out.println(type.ordinal() + 1 + ". " + type);
            }
            int typeOption = scanner.nextInt();
            scanner.nextLine();
            EventType type = EventType.values()[typeOption - 1];

            System.out.print("Enter Start DateTime (yyyy-MM-dd HH:mm): ");
            String start = scanner.nextLine();
            LocalDateTime startDateTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("Enter End DateTime (yyyy-MM-dd HH:mm): ");
            String end = scanner.nextLine();
            LocalDateTime endDateTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("Enter Venue ID: ");
            String venueId = scanner.nextLine();

            Venue venue = venueService.getVenueById(venueId);

            if (venue == null) {
                System.out.println("❌ Venue not found. Please create it first.");
                return;
            }

            Event event = new Event(id, name, type, startDateTime, endDateTime, venue);
            events.put(id, event);
            System.out.println("✅ Event created successfully!");
            System.out.println("📌 Event ID: " + id);
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Please enter correct options.");
            scanner.nextLine(); // limpiar el buffer
        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format. Use yyyy-MM-dd HH:mm.");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void modifyEvent() {
        try {
            System.out.println("\n🔹 Modifying an existing event...");
            System.out.print("Enter Event ID to modify: ");
            String id = scanner.nextLine();

            if (!events.containsKey(id)) {
                System.out.println("❌ Event not found.");
                return;
            }

            Event event = events.get(id);

            System.out.print("Enter new Event Name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                event.setName(name);
            }

            System.out.print("Enter new Venue ID (leave blank to keep current): ");
            String venueId = scanner.nextLine();
            if (!venueId.isEmpty()) {
                Venue venue = venueService.getVenueById(venueId);
                if (venue != null) {
                    event.setVenue(venue);
                } else {
                    System.out.println("❌ Venue not found. Keeping current venue.");
                }
            }

            System.out.println("✅ Event updated successfully!");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void deleteEvent() {
        try {
            System.out.println("\n🔹 Deleting an event...");
            System.out.print("Enter Event ID to delete: ");
            String id = scanner.nextLine();

            if (events.remove(id) != null) {
                System.out.println("✅ Event deleted successfully!");
            } else {
                System.out.println("❌ Event not found.");
            }
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void categorizeEvent() {
        try {
            System.out.println("\n🔹 Categorizing an event...");
            System.out.print("Enter Event ID to categorize: ");
            String id = scanner.nextLine();

            if (!events.containsKey(id)) {
                System.out.println("❌ Event not found.");
                return;
            }

            Event event = events.get(id);

            System.out.println("Select new Event Type:");
            for (EventType type : EventType.values()) {
                System.out.println(type.ordinal() + 1 + ". " + type);
            }
            int typeOption = scanner.nextInt();
            scanner.nextLine();
            EventType type = EventType.values()[typeOption - 1];

            event.setType(type);
            System.out.println("✅ Event type updated successfully!");
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid option selected.");
            scanner.nextLine(); // limpiar el buffer
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void registerBudget() {
        try {
            System.out.println("\n💵 Registering budget for an event...");
            System.out.print("Enter Event ID: ");
            String id = scanner.nextLine();

            if (!events.containsKey(id)) {
                System.out.println("❌ Event not found.");
                return;
            }

            System.out.print("Enter Budget Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            budgets.put(id, amount);
            System.out.println("✅ Budget registered successfully!");
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid amount entered.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void trackIncome() {
        try {
            System.out.println("\n💵 Tracking income...");
            System.out.print("Enter Event ID: ");
            String id = scanner.nextLine();

            if (!budgets.containsKey(id)) {
                System.out.println("❌ No budget registered for this event.");
                return;
            }

            System.out.print("Enter Income Amount: ");
            double income = scanner.nextDouble();
            scanner.nextLine();

            budgets.put(id, budgets.get(id) + income);
            System.out.println("✅ Income added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid income amount.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void trackExpense() {
        try {
            System.out.println("\n💵 Tracking expense...");
            System.out.print("Enter Event ID: ");
            String id = scanner.nextLine();

            if (!budgets.containsKey(id)) {
                System.out.println("❌ No budget registered for this event.");
                return;
            }

            System.out.print("Enter Expense Amount: ");
            double expense = scanner.nextDouble();
            scanner.nextLine();

            budgets.put(id, budgets.get(id) - expense);
            System.out.println("✅ Expense deducted successfully!");
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid expense amount.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void createTicketType() {
        try {
            System.out.println("\n🎟️ Creating a new ticket type...");
            System.out.print("Enter Event ID: ");
            String id = scanner.nextLine();

            if (!events.containsKey(id)) {
                System.out.println("❌ Event not found.");
                return;
            }

            System.out.print("Enter Ticket Type (e.g., VIP, General): ");
            String ticketType = scanner.nextLine();

            ticketSales.putIfAbsent(id, new ArrayList<>());
            ticketSales.get(id).add(ticketType);
            System.out.println("✅ Ticket type added successfully!");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void registerSale() {
        try {
            System.out.println("\n🎟️ Registering a ticket sale...");
            System.out.print("Enter Event ID: ");
            String id = scanner.nextLine();

            if (!ticketSales.containsKey(id)) {
                System.out.println("❌ No ticket types available for this event.");
                return;
            }

            System.out.print("Enter Sold Ticket Type: ");
            String soldTicket = scanner.nextLine();

            ticketSales.get(id).add(soldTicket);
            System.out.println("✅ Ticket sale registered successfully!");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events.values());
    }

    public Map<String, Event> getEvents() {
        return events;
    }
}
