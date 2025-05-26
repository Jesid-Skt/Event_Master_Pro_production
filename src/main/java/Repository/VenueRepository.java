package Repository;

import DTOS.VenueDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VenueRepository {
    private List<VenueDTO> venueList = new ArrayList<>();
    private final String FILE_PATH = "venues.txt";

    public void addVenue(VenueDTO venue) {
        venueList.add(venue);
        saveToFile();
    }

    public VenueDTO findById(String id) {
        for (VenueDTO v : venueList) {
            if (v.getVenueId().equals(id)) {
                return v;
            }
        }
        return null;
    }

    public List<VenueDTO> getAllVenues() {
        return venueList;
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (VenueDTO venue : venueList) {
                writer.write(String.join(";",
                        venue.getVenueId(),
                        venue.getName(),
                        venue.getLocation(),
                        String.valueOf(venue.getCapacity())));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        venueList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    VenueDTO venue = new VenueDTO();
                    venue.setVenueId(parts[0]);
                    venue.setName(parts[1]);
                    venue.setLocation(parts[2]);
                    venue.setCapacity(Integer.parseInt(parts[3]));
                    venueList.add(venue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

