package Services;

import Model.EventPackage.Availability;
import Model.EventPackage.Location;
import Model.EventPackage.Venue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class VenueService {

    private final Map<String, Venue> venues = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

  public void createVenue() {
        try {
            System.out.println("\n🏟️ Creating a new venue...");

            // Generar ID único automáticamente
            String id = UUID.randomUUID().toString().substring(0, 8);

            System.out.print("Enter Venue Name: ");
            String name = scanner.nextLine();

            if (name.isEmpty()) {
                System.out.println("❌ Venue name cannot be empty.");
                return;
            }

            // Location details
            System.out.println("\nLocation Details:");
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            System.out.print("Enter City: ");
            String city = scanner.nextLine();

            System.out.print("Enter Country: ");
            String country = scanner.nextLine();

            System.out.print("Enter Contact Info: ");
            String contactInfo = scanner.nextLine();

            System.out.print("Enter Venue Capacity: ");
            int capacity = Integer.parseInt(scanner.nextLine());

            if (capacity <= 0) {
                System.out.println("❌ Capacity must be greater than 0.");
                return;
            }

            Location location = new Location(address, city, country, contactInfo);
            Venue venue = new Venue(id, name, location, capacity);
            venues.put(id, venue);

            // Verificación de guardado
            if (venues.containsKey(id)) {
                System.out.println("✅ Venue created successfully!");
                System.out.println("📌 Venue details:");
                System.out.println("ID: " + venue.getId());
                System.out.println("Name: " + venue.getName());
                System.out.println("Address: " + venue.getLocation().getAddress());
                System.out.println("Capacity: " + venue.getCapacity());
                System.out.println("Total venues in system: " + venues.size());
            } else {
                System.out.println("❌ Error: Venue was not stored correctly.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Capacity must be a number.");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void addVenueAvailability() {
        try {
            System.out.println("\n🔹 Adding venue availability...");
            System.out.print("Enter Venue ID: ");
            String id = scanner.nextLine();

            Venue venue = venues.get(id);
            if (venue == null) {
                System.out.println("❌ Venue not found.");
                return;
            }

            System.out.println("Enter start date and time (format: yyyy-MM-dd HH:mm):");
            String startDateStr = scanner.nextLine();
            LocalDateTime startDate = LocalDateTime.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.println("Enter end date and time (format: yyyy-MM-dd HH:mm):");
            String endDateStr = scanner.nextLine();
            LocalDateTime endDate = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            if (endDate.isBefore(startDate)) {
                System.out.println("❌ End date cannot be before start date.");
                return;
            }

            Availability availability = new Availability(startDate, endDate, true);
            venue.addAvailability(availability);
            System.out.println("✅ Availability added successfully!");

        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format. Please use yyyy-MM-dd HH:mm");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void removeLocation() {
        try {
            System.out.println("\n🔹 Removing venue location...");
            System.out.print("Enter Venue ID: ");
            String id = scanner.nextLine();

            Venue venue = venues.get(id);
            if (venue == null) {
                System.out.println("❌ Venue not found.");
                return;
            }

            System.out.println("Current location: " + venue.getLocation().toString());
            System.out.print("Are you sure you want to remove this location? (y/n): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                venue.setLocation(null);
                System.out.println("✅ Location removed successfully!");
            } else {
                System.out.println("Operation cancelled.");
            }
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void modifyLocationDetails() {
        try {
            System.out.println("\n🔹 Modifying venue location details...");
            System.out.print("Enter Venue ID: ");
            String id = scanner.nextLine();

            Venue venue = venues.get(id);
            if (venue == null) {
                System.out.println("❌ Venue not found.");
                return;
            }

            Location currentLocation = venue.getLocation();
            System.out.println("\nCurrent location details:");
            System.out.println("Address: " + currentLocation.getAddress());
            System.out.println("City: " + currentLocation.getCity());
            System.out.println("Country: " + currentLocation.getCountry());
            System.out.println("Contact Info: " + currentLocation.getContactInfo());

            System.out.println("\nEnter new details (press Enter to keep current value):");

            System.out.print("New Address [" + currentLocation.getAddress() + "]: ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) {
                currentLocation.setAddress(address);
            }

            System.out.print("New City [" + currentLocation.getCity() + "]: ");
            String city = scanner.nextLine();
            if (!city.isEmpty()) {
                currentLocation.setCity(city);
            }

            System.out.print("New Country [" + currentLocation.getCountry() + "]: ");
            String country = scanner.nextLine();
            if (!country.isEmpty()) {
                currentLocation.setCountry(country);
            }

            System.out.print("New Contact Info [" + currentLocation.getContactInfo() + "]: ");
            String contactInfo = scanner.nextLine();
            if (!contactInfo.isEmpty()) {
                currentLocation.setContactInfo(contactInfo);
            }

            System.out.println("✅ Location details updated successfully!");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void modifyVenueAvailability() {
        try {
            System.out.println("\n🔹 Modifying venue availability...");
            System.out.print("Enter Venue ID: ");
            String id = scanner.nextLine();

            Venue venue = venues.get(id);
            if (venue == null) {
                System.out.println("❌ Venue not found.");
                return;
            }

            List<Availability> availabilities = venue.getAvailabilities();
            if (availabilities.isEmpty()) {
                System.out.println("❗ No availability periods to modify.");
                return;
            }

            showVenueAvailability();

            System.out.print("Enter the number of the availability period to modify: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            if (index < 0 || index >= availabilities.size()) {
                System.out.println("❌ Invalid availability period number.");
                return;
            }

            Availability availability = availabilities.get(index);

            System.out.println("\nEnter new dates (press Enter to keep current value)");
            System.out.println("Current start date: " + availability.getStartDate());
            System.out.print("New start date (yyyy-MM-dd HH:mm): ");
            String startDateStr = scanner.nextLine();

            System.out.println("Current end date: " + availability.getEndDate());
            System.out.print("New end date (yyyy-MM-dd HH:mm): ");
            String endDateStr = scanner.nextLine();

            if (!startDateStr.isEmpty()) {
                LocalDateTime startDate = LocalDateTime.parse(startDateStr,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                availability.setStartDate(startDate);
            }

            if (!endDateStr.isEmpty()) {
                LocalDateTime endDate = LocalDateTime.parse(endDateStr,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                availability.setEndDate(endDate);
            }

            System.out.println("✅ Availability period modified successfully!");
        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format. Please use yyyy-MM-dd HH:mm");
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number format.");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void showVenueAvailability() {
        try {
            System.out.println("\n🔹 Showing venue availability...");
            System.out.print("Enter Venue ID: ");
            String id = scanner.nextLine();

            Venue venue = venues.get(id);
            if (venue == null) {
                System.out.println("❌ Venue not found.");
                return;
            }

            List<Availability> availabilities = venue.getAvailabilities();
            if (availabilities.isEmpty()) {
                System.out.println("❗ No availability periods registered.");
                return;
            }

            System.out.println("\n📅 Availability periods for " + venue.getName() + ":");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            for (int i = 0; i < availabilities.size(); i++) {
                Availability av = availabilities.get(i);
                System.out.printf("%d. %s to %s - %s%n",
                        i + 1,
                        av.getStartDate().format(formatter),
                        av.getEndDate().format(formatter),
                        av.isAvailable() ? "Available" : "Not Available"
                );
            }
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }
    public void removeVenue() {
        try {
            System.out.println("\n🗑️ Removing venue...");
            System.out.print("Enter Venue ID: ");
            String id = scanner.nextLine();

            Venue venue = venues.get(id);
            if (venue == null) {
                System.out.println("❌ Venue not found.");
                return;
            }

            // Show venue details before confirmation
            System.out.println("\nVenue to remove:");
            System.out.println("ID: " + venue.getId());
            System.out.println("Name: " + venue.getName());
            System.out.println("Address: " + venue.getLocation().getAddress());
            System.out.println("Capacity: " + venue.getCapacity());

            System.out.print("\nAre you sure you want to remove this venue? (y/n): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                venues.remove(id);
                System.out.println("✅ Venue removed successfully!");
            } else {
                System.out.println("Operation cancelled.");
            }

        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void removeVenueAvailability() {
        try {
            System.out.println("\n🔹 Removing venue availability...");
            System.out.print("Enter Venue ID: ");
            String id = scanner.nextLine();

            Venue venue = venues.get(id);
            if (venue == null) {
                System.out.println("❌ Venue not found.");
                return;
            }

            List<Availability> availabilities = venue.getAvailabilities();
            if (availabilities.isEmpty()) {
                System.out.println("❗ No availability periods to remove.");
                return;
            }

            showVenueAvailability();

            System.out.print("Enter the number of the availability period to remove: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            if (index < 0 || index >= availabilities.size()) {
                System.out.println("❌ Invalid availability period number.");
                return;
            }

            Availability removedAvailability = availabilities.get(index);
            venue.removeAvailability(removedAvailability);
            System.out.println("✅ Availability period removed successfully!");

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number format.");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }
    public void showVenueDetails() {
        try {
            System.out.println("\n🔍 Showing venue details...");
            System.out.print("Enter Venue ID: ");
            String id = scanner.nextLine();

            Venue venue = venues.get(id);
            if (venue == null) {
                System.out.println("❌ Venue not found.");
                return;
            }

            // Basic venue information
            System.out.println("\n📌 VENUE INFORMATION");
            System.out.println("====================");
            System.out.println("ID: " + venue.getId());
            System.out.println("Name: " + venue.getName());
            System.out.println("Capacity: " + venue.getCapacity());

            // Location information
            Location location = venue.getLocation();
            if (location != null) {
                System.out.println("\n📍 LOCATION DETAILS");
                System.out.println("====================");
                System.out.println("Address: " + location.getAddress());
                System.out.println("City: " + location.getCity());
                System.out.println("Country: " + location.getCountry());
                System.out.println("Contact Info: " + location.getContactInfo());
            } else {
                System.out.println("\n❌ No location information registered.");
            }

            // Availability information
            List<Availability> availabilities = venue.getAvailabilities();
            if (!availabilities.isEmpty()) {
                System.out.println("\n📅 AVAILABILITY PERIODS");
                System.out.println("====================");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                for (int i = 0; i < availabilities.size(); i++) {
                    Availability av = availabilities.get(i);
                    System.out.printf("%d. From: %s%n   To: %s%n   Status: %s%n%n",
                            i + 1,
                            av.getStartDate().format(formatter),
                            av.getEndDate().format(formatter),
                            av.isAvailable() ? "Available" : "Not available"
                    );
                }
            } else {
                System.out.println("\n❌ No availability periods registered.");
            }

        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void listVenues() {
        System.out.println("\n🏟️ Available Venues:");
        if (venues.isEmpty()) {
            System.out.println("No venues registered.");
        } else {
            for (Venue venue : venues.values()) {
               System.out.println("- ID: " + venue.getId() + " | Name: " + venue.getName() + " | Address: " + venue.getLocation().getAddress() + " | Capacity: " + venue.getCapacity());
            }
        }
    }

    public Venue getVenueById(String id) {
        return venues.get(id);
    }

    public boolean venueExists(String id) {
        return venues.containsKey(id);
    }
}
