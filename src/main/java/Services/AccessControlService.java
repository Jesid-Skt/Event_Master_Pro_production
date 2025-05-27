package Services;

import Model.AccessPackage.AccessControl;
import Model.AccessPackage.Attendee;
import Repository.AccessControlRepository;

import java.util.List;


public class AccessControlService {
    private final AccessControl accessControl = new AccessControl();
    private AccessControlRepository repository = new AccessControlRepository();



    public boolean registerNewAttendee(String eventId, String attendeeId, String name, String email) {
        List<Attendee> attendees = accessControl.getAttendeesByEvent(eventId);
        if (attendees != null) {
            for (Attendee a : attendees) {
                if (a.getAttendeeId().equals(attendeeId)) {
                    System.out.println("❌ El asistente ya está registrado.");
                    return false;
                }
            }
        }
        Attendee attendee = new Attendee(attendeeId, name, email);
        accessControl.registerAttendee(eventId, attendee);
        repository.saveToFile(accessControl.getAttendeesByEventMap());
        System.out.println("✅ Attendee registered successfully.");
        return true;
    }

    public boolean validateAttendeeEntryByTicketCode(String ticketCode) {
        // Busca el asistente y el evento por el código del ticket
        for (String eventId : accessControl.getAttendeesByEventMap().keySet()) {
            List<Attendee> attendees = accessControl.getAttendeesByEvent(eventId);
            if (attendees != null) {
                for (Attendee attendee : attendees) {
                    if (attendee.getAttendeeId().equals(ticketCode)) {
                        // Valida la entrada usando el eventId y el ticketCode
                        return accessControl.validateEntry(eventId, ticketCode);
                    }
                }
            }
        }
        // No se encontró el ticket
        return false;
    }

    public String getAllAttendeesAsString(String eventId) {
        List<Attendee> attendees = accessControl.getAttendeesByEvent(eventId);
        if (attendees == null || attendees.isEmpty()) {
            return "No hay asistentes registrados para este evento.";
        }
        StringBuilder sb = new StringBuilder();
        for (Attendee attendee : attendees) {
            sb.append(attendee.toString()).append("\n");
        }
        return sb.toString();
    }

    public List<Attendee> getAllAttendees(String eventId) {
        return accessControl.getAttendeesByEvent(eventId);
    }
}