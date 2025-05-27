package Repository;

import DTOS.VenueDTO;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VenueRepository {
    private final com.google.gson.Gson gson = new com.google.gson.Gson();
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
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(venueList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        venueList.clear();
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<VenueDTO>>() {}.getType();
            List<VenueDTO> loadedList = gson.fromJson(reader, listType);
            if (loadedList != null) {
                venueList.addAll(loadedList);
            }
        } catch (FileNotFoundException e) {
            // Si el archivo no existe, la lista queda vacía
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

