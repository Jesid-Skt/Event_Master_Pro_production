package Services;

import Model.AccessPackage.AccessControl;
import Model.AccessPackage.Attendee;
import java.util.List;


public class AccessControlService {
    private final AccessControl accessControl = new AccessControl();

    public void registerNewAttendee(String eventId, String attendeeId, String name, String email) {
        Attendee attendee = new Attendee(attendeeId, name, email);
        accessControl.registerAttendee(eventId, attendee);
        System.out.println("✅ Attendee registered successfully.");
    }

    public boolean validateAttendeeEntry(String eventId, String attendeeId) {
        return accessControl.validateEntry(eventId, attendeeId);
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