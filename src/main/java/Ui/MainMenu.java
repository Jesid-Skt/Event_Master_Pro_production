package Ui;

import Services.*;
import Services.ArtistService;
import Services.EventService;
import Services.FinanceService;
import Services.TicketService;
import Services.VenueService;


import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final VenueService venueService = new VenueService();
    private static final EventService eventService = new EventService(venueService);
    private static final ArtistService artistService = new ArtistService();
    private static final FinanceService financeService = new FinanceService();
    private static final TicketService ticketService = new TicketService();
    private static final AccessControlService accessControlService = new AccessControlService();


    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("\n===== EventMaster Pro - Main Menu =====");
                System.out.println("1. Manage Events");
                System.out.println("2. Manage Artists");
                System.out.println("3. Manage Financials");
                System.out.println("4. Manage Ticket Sales");
                System.out.println("5. Manage Venues");
                System.out.println("6. Access Control");
                System.out.println("7. Exit");
                System.out.print("Select an option: ");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        manageEvents();
                        break;
                    case 2:
                        manageArtists();
                        break;
                    case 3:
                        manageFinancials();
                        break;
                    case 4:
                        manageTicketSales();
                        break;
                    case 5:
                        manageVenues();
                        break;
                    case 6:
                        manageAccessControl();
                        break;
                    case 7:  // Actualizado a 7
                        exit = true;
                        System.out.println("Exiting EventMaster Pro. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ An unexpected error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

   private static void manageEvents() {
        try {
            System.out.println("\n--- Manage Events ---");
            System.out.println("1. Create Event");
            System.out.println("2. Modify Event");
            System.out.println("3. Delete Event");
            System.out.println("4. Categorize Event");
            System.out.println("5. View Calendar"); // Nueva opción
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    eventService.createEvent();
                    break;
                case 2:
                    eventService.modifyEvent();
                    break;
                case 3:
                    eventService.deleteEvent();
                    break;
                case 4:
                    eventService.categorizeEvent();
                    break;
                case 5:
                    CalendarView calendarView = new CalendarView(eventService);
                    calendarView.showCalendar();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid option. Returning to main menu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Returning to main menu.");
            scanner.nextLine();
        }
    }
    private static void manageArtists() {
        try {
            System.out.println("\n--- Manage Events ---");
            System.out.println("1. Register Artist");
            System.out.println("2. Modify Artist");
            System.out.println("3. Delete Artist");
            System.out.println("5. add Artist Participation");
            System.out.println("4. Show information contact of Artist");
            System.out.println("6. Show Technical Requirements");
            System.out.println("7. Show Artist Details");
            System.out.println("8. List Artists");
            System.out.println("9. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    artistService.registerArtist();
                    break;
                case 2:
                    artistService.modifyArtist();
                    break;
                case 3:
                    artistService.deleteArtist();
                    break;
                case 4:
                    artistService.addParticipationHistory();
                    break;
                case 5:
                    artistService.showArtistContactInfo();
                    break;
                case 6:
                    artistService.showTechnicalRequirements();
                    break;
                case 7:
                    artistService.showArtistDetails();
                    break;
                case 8:
                    artistService.listArtists();
                    break;
                    case 9:
                        break;
                default:
                    System.out.println("Invalid option. Returning to main menu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Returning to main menu.");
            scanner.nextLine();
        }
    }

    private static void manageFinancials() {
        try {
            System.out.println("\n--- Manage Financials ---");
            System.out.println("1. Register Budget");
            System.out.println("2. Track Income");
            System.out.println("3. Track Expenses");
            System.out.println("4. Show Income Report");
            System.out.println("5. Show Expenses Report");
            System.out.println("6. Show Financial Summary");
            System.out.println("7. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    financeService.registerBudget();
                    break;
                case 2:
                    financeService.trackIncome();
                    break;
                case 3:
                    financeService.trackExpense();
                    break;
                case 4:
                    financeService.viewIncome();
                    break;
                case 5:
                    financeService.viewExpenses();
                    break;
                case 6:
                    financeService.viewFinancialSummary();
                    break;
                    case 7:
                        break;
                default:
                    System.out.println("Invalid option. Returning to main menu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Returning to main menu.");
            scanner.nextLine();
        }
    }

    private static void manageTicketSales() {
        try {
            System.out.println("\n--- Manage Ticket Sales ---");
            System.out.println("1. Create Ticket Type");
            System.out.println("2. Register Ticket Sale");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ticketService.createTicketType();
                    break;
                case 2:
                    ticketService.registerSale();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option. Returning to main menu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Returning to main menu.");
            scanner.nextLine();
        }
    }

   private static void manageVenues() {
        try {
            System.out.println("\n--- Manage Venues ---");
            System.out.println("1. Create Venue");
            System.out.println("2. Add Venue Availability");
            System.out.println("3. Modify Venue Availability");
            System.out.println("4. Modify Location Details");
            System.out.println("5. Remove Venue Availability");
            System.out.println("6. Remove Location");
            System.out.println("7. Remove Venue");
            System.out.println("8. List Venues");
            System.out.println("9. Show Venue Details");
            System.out.println("10. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    venueService.createVenue();
                    break;
                case 2:
                    venueService.addVenueAvailability();
                    break;
                case 3:
                    venueService.modifyVenueAvailability();
                    break;
                case 4:
                    venueService.modifyLocationDetails();
                    break;
                case 5:
                    venueService.removeVenueAvailability();
                    break;
                case 6:
                    venueService.removeVenue();
                    break;
                case 7:
                    venueService.removeLocation();
                    break;
                case 8:
                    venueService.listVenues();
                    break;
                case 9:
                    venueService.showVenueDetails();
                    break;
                case 10:
                    break;
                default:
                    System.out.println("Invalid option. Returning to main menu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Returning to main menu.");
            scanner.nextLine();
        }
    }

    private static void manageAccessControl() {
        try {
            System.out.println("\n--- Manage Access Control ---");
            System.out.println("1. Register Attendee");
            System.out.println("2. Validate Entry");
            System.out.println("3. Show Attendance Statistics");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    accessControlService.registerNewAttendee();
                    break;
                case 2:
                    accessControlService.validateAttendeeEntry();
                    break;
                case 3:
                    accessControlService.showAttendanceStatistics();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid option. Returning to main menu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Returning to main menu.");
            scanner.nextLine();
        }
    }
}
