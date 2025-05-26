package Services;

import DTOS.EventDTO;
import Model.EventPackage.Event;
import Model.EventPackage.Venue;
import Enums.EventType;
import Repository.EventRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EventService {

    private final Map<String, Event> events = new HashMap<>();
    private final EventRepository repository = new EventRepository();
    private final Map<String, Double> budgets = new HashMap<>();
    private final Map<String, List<String>> ticketSales = new HashMap<>();
    private final VenueService venueService;

    public EventService(VenueService venueService) {
        this.venueService = venueService;
    }

    // Ahora recibe un DTO en vez de parámetros separados
    public Event createEvent(EventDTO dto) throws Exception {
        // Validaciones
        if (dto.getEventName() == null || dto.getEventName().trim().isEmpty())
            throw new IllegalArgumentException("Nombre del evento no puede estar vacío");
        if (dto.getEventType() == null)
            throw new IllegalArgumentException("Tipo de evento no puede ser nulo");
        if (dto.getVenueId() == null || dto.getVenueId().trim().isEmpty())
            throw new IllegalArgumentException("ID de venue no puede estar vacío");

        Venue venue = venueService.getVenueById(dto.getVenueId());
        if (venue == null)
            throw new Exception("El recinto no existe.");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(dto.getStartDate(), formatter);
        LocalDateTime endDate = LocalDateTime.parse(dto.getEndDate(), formatter);

        if (endDate.isBefore(startDate))
            throw new IllegalArgumentException("La fecha final no puede ser anterior a la inicial.");

        String id = UUID.randomUUID().toString().substring(0, 8);

        Event event = new Event(id, dto.getEventName(), EventType.valueOf(dto.getEventType()), startDate, endDate, venue);
        events.put(id, event);

        return event;
    }


    public boolean deleteEventByName(String name) {
        if (name == null || name.trim().isEmpty()) return false;

        repository.loadFromFile();
        List<EventDTO> allEvents = new ArrayList<>(repository.getAllEvents().values());

        boolean found = false;
        Iterator<EventDTO> iterator = allEvents.iterator();
        while (iterator.hasNext()) {
            EventDTO event = iterator.next();
            if (event.getEventName().equalsIgnoreCase(name.trim())) {
                iterator.remove();
                found = true;
                break; // elimina solo el primero que coincida
            }
        }

        if (found) {
            repository.setAllEvents(allEvents); // sobrescribe lista interna
            repository.saveToFile();            // guarda cambios
            return true;
        }

        return false;
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
    public String generateUniqueEventID() {
        // Ejemplo simple: UUID
        return "EVT-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public Map<String, Event> getEvents() {
        return events;
    }
}
