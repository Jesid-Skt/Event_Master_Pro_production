package Services;

import Model.AccessPackage.AccessControl;
import Model.AccessPackage.Attendee;
import java.util.List;

import java.util.Scanner;

public class AccessControlService {
    private AccessControl accessControl = new AccessControl();
    private Scanner scanner = new Scanner(System.in);

    public void registerNewAttendee(String eventId, String attendeeId, String name, String email) {
        Attendee attendee = new Attendee(attendeeId, name, email);
        accessControl.registerAttendee(eventId, attendee);
        System.out.println("✅ Attendee registered successfully.");
    }

    public boolean validateAttendeeEntry(String eventId, String attendeeId) {
        boolean success = accessControl.validateEntry(eventId, attendeeId);
        return success;
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

    public void showAllAttendees(String eventId) {
        accessControl.showAllAttendees(eventId);
    }
}