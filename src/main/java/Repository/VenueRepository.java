package Repository;

import DTOS.VenueDTO;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VenueRepository {
    private final Gson gson = new Gson();
    private List<VenueDTO> venueList = new ArrayList<>();
    private final String FILE_PATH = "venues.json";

    public void addVenue(VenueDTO venue) {
        venueList.add(venue);
        saveToFile();
    }

    public VenueDTO findById(String id) {
        for (VenueDTO v : venueList) {
            if (v.getVenueId().trim().equalsIgnoreCase(id.trim())) {
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
    public void removeVenueById(String id) {
        getAllVenues().remove(id);  // Asumiendo que tienes un Map<String, VenueDTO> allVenues
        saveToFile();          // Método que guarda todos los venues actualizados en el archivo
    }

    public void updateVenue(VenueDTO updatedVenue) {
        for (int i = 0; i < venueList.size(); i++) {
            VenueDTO current = venueList.get(i);
            if (current.getVenueId().trim().equalsIgnoreCase(updatedVenue.getVenueId().trim())) {
                venueList.set(i, updatedVenue);  // Reemplaza el venue existente con el actualizado
                saveToFile();                    // Guarda cambios en el archivo
                return;
            }
        }
        System.out.println("⚠️ No se encontró el venue con ID: " + updatedVenue.getVenueId());
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
