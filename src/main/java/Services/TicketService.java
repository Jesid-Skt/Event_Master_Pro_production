package Services;


import java.util.*;

public class TicketService {

    private final Map<String, List<String>> ticketTypesByEvent = new HashMap<>();
    private final Map<String, Integer> ticketSalesByEvent = new HashMap<>();

    /**
     * Crea un nuevo tipo de ticket para un evento.
     * Genera un nuevo eventId automáticamente.
     * @param ticketType Nombre del tipo de ticket (VIP, General, etc)
     * @return eventId generado para el evento
     * @throws IllegalArgumentException si ticketType es null o vacío
     */
    public String createTicketType(String ticketType) {
        if (ticketType == null || ticketType.trim().isEmpty()) {
            throw new IllegalArgumentException("Ticket type cannot be empty.");
        }
        String eventId = UUID.randomUUID().toString().substring(0, 8);
        ticketTypesByEvent.putIfAbsent(eventId, new ArrayList<>());
        ticketTypesByEvent.get(eventId).add(ticketType.trim());
        return eventId;
    }

    /**
     * Registra una venta de ticket para un evento específico.
     * @param eventId Id del evento
     * @param ticketType Tipo de ticket vendido
     * @return true si la venta fue registrada, false si no existe ese evento o tipo de ticket
     */
    public boolean registerSale(String eventId, String ticketType) {
        if (eventId == null || ticketType == null) return false;

        List<String> types = ticketTypesByEvent.get(eventId);
        if (types == null || !types.contains(ticketType)) {
            return false; // No existe evento o tipo de ticket
        }

        ticketSalesByEvent.put(eventId, ticketSalesByEvent.getOrDefault(eventId, 0) + 1);
        return true;
    }

    public Map<String, List<String>> getTicketTypesByEvent() {
        return Collections.unmodifiableMap(ticketTypesByEvent);
    }

    public Map<String, Integer> getTicketSalesByEvent() {
        return Collections.unmodifiableMap(ticketSalesByEvent);
    }
}