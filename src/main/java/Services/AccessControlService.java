package Services;

import Model.AccessPackage.AccessControl;
import Model.AccessPackage.Attendee;

import java.util.Scanner;

public class AccessControlService {
    private AccessControl accessControl = new AccessControl();
    private Scanner scanner = new Scanner(System.in);

    public void registerNewAttendee() {
        System.out.println("\n🔹 Registering a new attendee...");
        System.out.print("Enter Event ID: ");
        String eventId = scanner.nextLine();
        System.out.print("Enter Attendee ID: ");
        String attendeeId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Attendee attendee = new Attendee(attendeeId, name, email);
        accessControl.registerAttendee(eventId, attendee);
        System.out.println("✅ Attendee registered successfully.");
    }

    public void validateAttendeeEntry() {
        System.out.println("\n🔹 Validating attendee entry...");
        System.out.print("Enter Event ID: ");
        String eventId = scanner.nextLine();
        System.out.print("Enter Attendee ID: ");
        String attendeeId = scanner.nextLine();

        boolean success = accessControl.validateEntry(eventId, attendeeId);
        if (success) {
            System.out.println("✅ Entry validated. Welcome!");
        } else {
            System.out.println("❌ Invalid entry or attendee already checked in.");
        }
    }

    public void showAttendanceStatistics() {
        System.out.println("\n🔹 Attendance statistics...");
        System.out.print("Enter Event ID: ");
        String eventId = scanner.nextLine();

        int total = accessControl.getTotalAttendees(eventId);
        int checkedIn = accessControl.getTotalCheckedIn(eventId);

        System.out.println("📈 Total registered attendees: " + total);
        System.out.println("📈 Total attendees checked in: " + checkedIn);
    }
}