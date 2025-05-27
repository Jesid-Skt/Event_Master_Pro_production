package Services;

import Enums.City;
import Enums.Country;
import Model.EventPackage.Availability;
import Model.EventPackage.Location;
import Model.EventPackage.Venue;
import java.time.LocalDateTime;

import java.util.*;

/**
 * Clase que gestiona los venues.
 * Permite crear, modificar y eliminar venues y sus disponibilidades.
 */

public class VenueService {

    private final Map<String, Venue> venues = new HashMap<>();

    // Crear un venue a partir de datos recibidos
    public String createVenue(String name, String address, City city, Country country, int capacity) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Venue name cannot be empty.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }

        String id = UUID.randomUUID().toString().substring(0, 8);
        Location location = new Location(address, city, country);
        Venue venue = new Venue(id, name, location, capacity);
        venues.put(id, venue);

        return id;  // Retorna el id generado para que la GUI lo muestre si quiere
    }

    public void addVenueAvailability(String venueId, LocalDateTime startDate, LocalDateTime endDate) throws NoSuchElementException, IllegalArgumentException {
        Venue venue = venues.get(venueId);
        if (venue == null) {
            throw new NoSuchElementException("Venue not found.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        Availability availability = new Availability(startDate, endDate, true);
        venue.addAvailability(availability);
    }

    public void removeLocation(String venueId) throws NoSuchElementException {
        Venue venue = venues.get(venueId);
        if (venue == null) {
            throw new NoSuchElementException("Venue not found.");
        }
        venue.setLocation(null);
    }

    public void modifyLocationDetails(String venueId, String address, City city, Country country) throws NoSuchElementException {
        Venue venue = venues.get(venueId);
        if (venue == null) {
            throw new NoSuchElementException("Venue not found.");
        }
        Location locat = venue.getLocation();
        if (locat == null) {
            locat = new Location(address, city, country);
            venue.setLocation(locat);
        } else {
            if (address != null && !address.isEmpty()) locat.setAddress(address);
           if (city != null) locat.setCity(city);
           if (country != null) locat.setCountry(country);
        }
    }

    public void modifyVenueAvailability(String venueId, int availabilityIndex, LocalDateTime newStartDate, LocalDateTime newEndDate) throws NoSuchElementException, IllegalArgumentException, IndexOutOfBoundsException {
        Venue venue = venues.get(venueId);
        if (venue == null) {
            throw new NoSuchElementException("Venue not found.");
        }

        List<Availability> availabilities = venue.getAvailabilities();
        if (availabilityIndex < 0 || availabilityIndex >= availabilities.size()) {
            throw new IndexOutOfBoundsException("Invalid availability period number.");
        }

        if (newStartDate != null && newEndDate != null && newEndDate.isBefore(newStartDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }

        Availability availability = availabilities.get(availabilityIndex);
        if (newStartDate != null) {
            availability.setStartDate(newStartDate);
        }
        if (newEndDate != null) {
            availability.setEndDate(newEndDate);
        }
    }

    public List<Availability> getVenueAvailability(String venueId) throws NoSuchElementException {
        Venue venue = venues.get(venueId);
        if (venue == null) {
            throw new NoSuchElementException("Venue not found.");
        }
        return Collections.unmodifiableList(venue.getAvailabilities());
    }

    public void removeVenue(String venueId) throws NoSuchElementException {
        if (!venues.containsKey(venueId)) {
            throw new NoSuchElementException("Venue not found.");
        }
        venues.remove(venueId);
    }

    public void removeVenueAvailability(String venueId, int availabilityIndex) throws NoSuchElementException, IndexOutOfBoundsException {
        Venue venue = venues.get(venueId);
        if (venue == null) {
            throw new NoSuchElementException("Venue not found.");
        }

        List<Availability> availabilities = venue.getAvailabilities();
        if (availabilityIndex < 0 || availabilityIndex >= availabilities.size()) {
            throw new IndexOutOfBoundsException("Invalid availability period number.");
        }
        Availability toRemove = availabilities.get(availabilityIndex);
        venue.removeAvailability(toRemove);
    }

    public Venue getVenueById(String id) {
        return venues.get(id);
    }

    public boolean venueExists(String id) {
        return venues.containsKey(id);
    }

    public Collection<Venue> listVenues() {
        return Collections.unmodifiableCollection(venues.values());
    }
}