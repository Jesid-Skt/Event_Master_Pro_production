package Services;

import DTOS.VenueDTO;
import Enums.City;
import Enums.Country;
import Model.EventPackage.Location;
import Model.EventPackage.Venue;
import Repository.VenueRepository;

import java.util.*;

public class VenueService {

    private final Map<String, Venue> venues = new HashMap<>();
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
        loadVenuesFromRepository(); // Cargar los venues al iniciar
    }

    private void loadVenuesFromRepository() {
        venueRepository.loadFromFile();
        for (VenueDTO dto : venueRepository.getAllVenues()) {
            Venue venue = Venue.fromDTO(dto);
            venues.put(venue.getId(), venue);
        }
    }

    public String createVenue(String name, String address, City city, Country country, int capacity) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Venue name cannot be empty.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }

        String id = generateUniqueVenueID();
        Location location = new Location(address, city, country);
        Venue venue = new Venue(id, name, country, city, capacity);
        venue.setLocation(location);

        venues.put(id, venue);

        // Guardar también en el repositorio
        VenueDTO dto = VenueDTO.fromVenue(venue);
        venueRepository.addVenue(dto);

        return id;
    }

    public void removeLocation(String venueId) throws NoSuchElementException {
        Venue venue = venues.get(venueId);
        if (venue == null) {
            throw new NoSuchElementException("Venue not found.");
        }
        venue.setCountry(null);
        venue.setCity(null);
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

    public void removeVenue(String venueId) throws NoSuchElementException {
        if (!venues.containsKey(venueId)) {
            throw new NoSuchElementException("Venue not found.");
        }
        venues.remove(venueId);
        // Si lo deseas, puedes también eliminar del archivo (opcional)
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

    public String generateUniqueVenueID() {
        return "EVT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
