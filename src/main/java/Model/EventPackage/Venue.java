package Model.EventPackage;

import DTOS.VenueDTO;
import Enums.City;
import Enums.Country;

public class Venue {
    private String id;
    private String name;
    private Country country;
    private City city;
    private int capacity;
    private Location location;

    public Venue(String id, String name, Country country, City city, int capacity) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.capacity = capacity;
    }

    public static Venue fromDTO(VenueDTO dto) {
        // dto ya devuelve enums, no es necesario usar valueOf
        Country country = dto.getCountry();
        City city = dto.getCity();

        Venue venue = new Venue(dto.getVenueId(), dto.getName(), country, city, dto.getCapacity());

        Location location = new Location(dto.getAddress(), city, country);
        venue.setLocation(location);

        return venue;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        if (location != null) {
            return location.getAddress();
        } else {
            return null;
        }
    }
}
