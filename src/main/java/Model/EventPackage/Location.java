package Model.EventPackage;

import Enums.City;
import Enums.Country;

public class Location {
    private String address;
    private Country country;
    private City city;

    public Location(String address, City city, Country country) {
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

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

    @Override
    public String toString() {
        return address + ", " + city + ", " + country;
    }
}
