package Model.EventPackage;


import Enums.City;
import Enums.Country;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

    public class Venue {
        private String id;
        private String name;
        private Country country;
        private City city;
        private int capacity;

        public Venue(String id, String name, Country country, City city , int capacity) {
            this.id = id;
            this.name = name;
            this.country = country;
            this.city = city;
            this.capacity = capacity;

        }

        private Location location;

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        // Getters y setters existentes
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public Country getCountry() { return country; }
        public void setCountry(Country country) { this.country = country; }
        public City getCity() { return city; }
        public void setCity(City city) { this.city = city; }

        public int getCapacity() { return capacity; }
        public void setCapacity(int capacity) { this.capacity = capacity; }

    }