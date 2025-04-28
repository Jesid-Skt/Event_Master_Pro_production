package Model.EventPackage;

    import Model.ArtistPackage.TechnicalFeature;

    import java.util.ArrayList;
    import java.util.List;

    public class Venue {
        private String id;
        private String name;
        private Location location;
        private int capacity;
        private List<TechnicalFeature> technicalFeatures;
        private List<Availability> availabilities;

        public Venue(String id, String name, Location location, int capacity) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.capacity = capacity;
            this.technicalFeatures = new ArrayList<>();
            this.availabilities = new ArrayList<>();
        }

        // Getters y setters existentes
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public Location getLocation() { return location; }
        public void setLocation(Location location) { this.location = location; }

        public int getCapacity() { return capacity; }
        public void setCapacity(int capacity) { this.capacity = capacity; }

        public List<TechnicalFeature> getTechnicalFeatures() { return technicalFeatures; }
        public void setTechnicalFeatures(List<TechnicalFeature> technicalFeatures) { this.technicalFeatures = technicalFeatures; }

        public List<Availability> getAvailabilities() { return availabilities; }
        public void setAvailabilities(List<Availability> availabilities) { this.availabilities = availabilities; }

        public void addAvailability(Availability availability) {
            this.availabilities.add(availability);
        }

        public void removeAvailability(Availability availability) {
            this.availabilities.remove(availability);
        }
    }