package DTOS;

import Enums.City;
import Enums.Country;
import Model.EventPackage.Venue;

public class VenueDTO {
    private String venueId;
    private String name;
    private String address;
    private Country country;
    private City city;
    private int capacity;

    public VenueDTO() {}

    public VenueDTO(String venueId, String name, Country country, City city, int capacity, String address) {
        this.venueId = venueId;
        this.name = name;
        this.address = address;
        this.country = country;
        this.city = city;
        this.capacity = capacity;
    }

    public static VenueDTO fromVenue(Venue venue) {
        return new VenueDTO(
                venue.getId(),
                venue.getName(),
                venue.getCountry(),
                venue.getCity(),
                venue.getCapacity(),
                venue.getAddress()
        );
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "VenueDTO{" +
                "venueId='" + venueId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", country=" + country +
                ", city=" + city +
                ", capacity=" + capacity +
                '}';
    }
}
