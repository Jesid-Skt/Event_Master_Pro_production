package DTOS;

import java.util.HashMap;
import java.util.List;

public class AccessControlDTO {
    private HashMap<String, List<AttendeeDTO>> attendeesByEvent;

    public AccessControlDTO() {
    }

    public AccessControlDTO(HashMap<String, List<AttendeeDTO>> attendeesByEvent) {
        this.attendeesByEvent = attendeesByEvent;
    }

    public HashMap<String, List<AttendeeDTO>> getAttendeesByEvent() {
        return attendeesByEvent;
    }

    public void setAttendeesByEvent(HashMap<String, List<AttendeeDTO>> attendeesByEvent) {
        this.attendeesByEvent = attendeesByEvent;
    }
}
