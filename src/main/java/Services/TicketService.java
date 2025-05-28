package Services;

import DTOS.TicketDTO;
import Enums.TicketType;
import Model.EventPackage.Event;
import Repository.TicketRepository;
import Repository.EventRepository;

import java.util.*;

public class TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final Map<String, List<String>> ticketSales = new HashMap<>();

    public TicketService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        this.ticketRepository = new TicketRepository();
        this.ticketRepository.loadFromFile();
    }

    public void addTicket(String code, TicketType type, boolean isSold, boolean isUsed, Event event) {
        double price = type.getPrice();
        TicketDTO ticket = new TicketDTO(code, type, price, isSold, isUsed, event);
        ticketRepository.addTicket(ticket);
    }

    public boolean createTicketType(String id, String ticketType) {
        if (eventRepository.getEventById(id) == null) return false;
        ticketSales.putIfAbsent(id, new ArrayList<>());
        ticketSales.get(id).add(ticketType);
        return true;
    }

    public boolean registerSale(String id, String soldTicket) {
        if (!ticketSales.containsKey(id)) return false;
        ticketSales.get(id).add(soldTicket);
        return true;
    }

    public TicketDTO getTicketByCode(String code) {
        return ticketRepository.findById(code);
    }

    public List<TicketDTO> getAllTickets() {
        return ticketRepository.getAllTicketsAsList();
    }

    public void saveTickets() {
        ticketRepository.saveToFile();
    }

    public void loadTickets() {
        ticketRepository.loadFromFile();
    }

    public static String generateTicketCode() {
        return "TICKET-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
