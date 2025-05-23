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
    private final VenueService venueService;

    public EventService(VenueService venueService) {
        this.venueService = venueService;
    }

    public Event createEvent(String name, EventType type, String startStr, String endStr, String venueId) throws Exception {
        try {
            String id = UUID.randomUUID().toString().substring(0, 8);
            LocalDateTime startDateTime = LocalDateTime.parse(startStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime endDateTime = LocalDateTime.parse(endStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            Venue venue = venueService.getVenueById(venueId);
            if (venue == null) {
                throw new Exception("❌ Venue not found. Please create it first.");
            }

            Event event = new Event(id, name, type, startDateTime, endDateTime, venue);
            events.put(id, event);
            return event;

        } catch (DateTimeParseException e) {
            throw new Exception("❌ Invalid date format. Use yyyy-MM-dd HH:mm.");
        }
    }

    public boolean modifyEvent(String id, String newName, String newVenueId) {
        if (!events.containsKey(id)) return false;

        Event event = events.get(id);
        if (newName != null && !newName.isEmpty()) {
            event.setName(newName);
        }

        if (newVenueId != null && !newVenueId.isEmpty()) {
            Venue venue = venueService.getVenueById(newVenueId);
            if (venue != null) {
                event.setVenue(venue);
            }
        }
        return true;
    }

    public boolean deleteEvent(String id) {
        return events.remove(id) != null;
    }

    public boolean categorizeEvent(String id, EventType type) {
        if (!events.containsKey(id)) return false;
        events.get(id).setType(type);
        return true;
    }

    public boolean registerBudget(String id, double amount) {
        if (!events.containsKey(id)) return false;
        budgets.put(id, amount);
        return true;
    }

    public boolean trackIncome(String id, double income) {
        if (!budgets.containsKey(id)) return false;
        budgets.put(id, budgets.get(id) + income);
        return true;
    }

    public boolean trackExpense(String id, double expense) {
        if (!budgets.containsKey(id)) return false;
        budgets.put(id, budgets.get(id) - expense);
        return true;
    }

    public boolean createTicketType(String id, String ticketType) {
        if (!events.containsKey(id)) return false;
        ticketSales.putIfAbsent(id, new ArrayList<>());
        ticketSales.get(id).add(ticketType);
        return true;
    }

    public boolean registerSale(String id, String soldTicket) {
        if (!ticketSales.containsKey(id)) return false;
        ticketSales.get(id).add(soldTicket);
        return true;
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events.values());
    }

    public Map<String, Event> getEvents() {
        return events;
    }
}
