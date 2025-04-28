package Model.AccessPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccessControl {
    private HashMap<String, List<Attendee>> attendeesByEvent = new HashMap<>();

    public void registerAttendee(String eventId, Attendee attendee) {
        attendeesByEvent.putIfAbsent(eventId, new ArrayList<>());
        attendeesByEvent.get(eventId).add(attendee);
    }

    public boolean validateEntry(String eventId, String attendeeId) {
        List<Attendee> attendees = attendeesByEvent.get(eventId);
        if (attendees != null) {
            for (Attendee attendee : attendees) {
                if (attendee.getAttendeeId().equals(attendeeId) && !attendee.isCheckedIn()) {
                    attendee.checkIn();
                    return true;
                }
            }
        }
        return false;
    }

    public int getTotalAttendees(String eventId) {
        List<Attendee> attendees = attendeesByEvent.get(eventId);
        return attendees != null ? attendees.size() : 0;
    }

    public int getTotalCheckedIn(String eventId) {
        List<Attendee> attendees = attendeesByEvent.get(eventId);
        if (attendees == null) return 0;

        int count = 0;
        for (Attendee attendee : attendees) {
            if (attendee.isCheckedIn()) {
                count++;
            }
        }
        return count;
    }
}
