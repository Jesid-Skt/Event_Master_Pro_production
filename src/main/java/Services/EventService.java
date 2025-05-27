// src/main/java/Services/EventService.java
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

        // Now receives a DTO instead of separate parameters
        public Event createEvent(EventDTO dto) throws Exception {
            // Validations
            if (dto.getEventName() == null || dto.getEventName().trim().isEmpty())
                throw new IllegalArgumentException("Event name cannot be empty");
            if (dto.getEventType() == null)
                throw new IllegalArgumentException("Event type cannot be null");
            if (dto.getVenueId() == null || dto.getVenueId().trim().isEmpty())
                throw new IllegalArgumentException("Venue ID cannot be empty");

            Venue venue = venueService.getVenueById(dto.getVenueId());
            if (venue == null)
                throw new Exception("The venue does not exist.");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startDate = LocalDateTime.parse(dto.getStartDate(), formatter);
            LocalDateTime endDate = LocalDateTime.parse(dto.getEndDate(), formatter);

            if (endDate.isBefore(startDate))
                throw new IllegalArgumentException("End date cannot be before start date.");

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
                    break; // deletes only the first match
                }
            }

            if (found) {
                repository.setAllEvents(allEvents); // overwrite internal list
                repository.saveToFile();            // save changes
                return true;
            }

            return false;
        }

        public boolean categorizeEvent(String id, EventType type) {
            if (!events.containsKey(id)) return false;
            events.get(id).setType(type);
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
            repository.loadFromFile();
            List<EventDTO> dtos = repository.getAllEventsAsList();
            List<Event> events = new ArrayList<>();
            for (EventDTO dto : dtos) {
                Event event = new Event(
                    dto.getEventId(),
                    dto.getEventName(),
                    EventType.valueOf(dto.getEventType()),
                    LocalDateTime.parse(dto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    LocalDateTime.parse(dto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    venueService.getVenueById(dto.getVenueId())
                );
                events.add(event);
            }
            return events;
        }

        public String generateUniqueEventID() {
            // Simple example: UUID
            return "EVT-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }

        public Map<String, Event> getEvents() {
            return events;
        }
    }