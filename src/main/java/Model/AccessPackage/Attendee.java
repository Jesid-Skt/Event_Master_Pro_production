package Model.AccessPackage;

public class Attendee {
    private String attendeeId;
    private String name;
    private String email;
    private boolean isCheckedIn;

    public Attendee(String attendeeId, String name, String email) {
        this.attendeeId = attendeeId;
        this.name = name;
        this.email = email;
        this.isCheckedIn = false;
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    public void checkIn() {
        this.isCheckedIn = true;
    }
}
