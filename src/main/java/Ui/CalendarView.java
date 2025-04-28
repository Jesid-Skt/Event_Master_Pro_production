package Ui;

import Model.EventPackage.Event;
import Model.EventPackage.Venue;
import Services.EventService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CalendarView {

    private final EventService eventService;
    private final Scanner scanner = new Scanner(System.in);

    public CalendarView(EventService eventService) {
        this.eventService = eventService;
    }

    public void showCalendar() {
        System.out.println("\n📅 --- Event Calendar ---");

        List<Event> events = eventService.getAllEvents(); // Método para traer todos los eventos

        if (events.isEmpty()) {
            System.out.println("No events scheduled.");
            return;
        }

        for (Event event : events) {
            Venue venue = event.getVenue();
            System.out.println("---------------------------------");
            System.out.println("Event Name: " + event.getName());
            System.out.println("Venue: " + (venue != null ? venue.getName() : "No venue assigned"));
            System.out.println("Start Date: " + event.getStartDateTime());
            System.out.println("End Date: " + event.getEndDateTime());
        }
        System.out.println("---------------------------------");

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(formatter);
    }
}
