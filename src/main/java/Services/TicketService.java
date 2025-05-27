package Services;

     import DTOS.TicketDTO;
     import Enums.TicketType;
     import Model.EventPackage.Event;
     import Repository.TicketRepository;

     import java.util.List;
     import java.util.UUID;

public class TicketService {

         private final TicketRepository ticketRepository = new TicketRepository();

         public TicketService() {
             ticketRepository.loadFromFile();
         }

         public void addTicket(String code, TicketType type, boolean isSold, boolean isUsed, Event event) {
             double price = type.getPrice();
             TicketDTO ticket = new TicketDTO(code, type, price, isSold, isUsed, event);
             ticketRepository.addTicket(ticket);
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