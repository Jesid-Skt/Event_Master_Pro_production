package DTOS;


import Model.AccessPackage.Attendee;

public class AttendeeDTO {
    private String attendeeId;
    private String name;
    private boolean checkedIn;

    public AttendeeDTO() {
    }

    public AttendeeDTO(String attendeeId, String name, boolean checkedIn) {
        this.attendeeId = attendeeId;
        this.name = name;
        this.checkedIn = checkedIn;
    }
    // Constructor que recibe un Attendee
    public AttendeeDTO(Attendee attendee) {
        this.attendeeId = attendee.getAttendeeId();
        this.name = attendee.getName();
        this.checkedIn = attendee.isCheckedIn();
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(String attendeeId) {
        this.attendeeId = attendeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    @Override
    public String toString() {
        return "AttendeeDTO{" +
                "attendeeId='" + attendeeId + '\'' +
                ", name='" + name + '\'' +
                ", checkedIn=" + checkedIn +
                '}';
    }
}