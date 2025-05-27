package Repository;

import DTOS.ArtistDTO;
import Model.ArtistPackage.Artist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ArtistRepository {
    private final String FILE_PATH = "artists.json";
    private List<ArtistDTO> artistList = new ArrayList<>();
    private final Gson gson = new Gson();

    public void addArtist(ArtistDTO artist) {
        artistList.add(artist);
        saveToFile();
    }

    public ArtistDTO findName(Artist artist) {
        return artistList.stream()
                .filter(a -> a.getNameArtist().equals(artist.getName()))
                .findFirst()
                .orElse(null);
    }

    public List<ArtistDTO> getAllArtists() {
        return artistList;
    }

    public void saveToFile() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(artistList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        artistList.clear();
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<ArtistDTO>>() {}.getType();
            List<ArtistDTO> loadedList = gson.fromJson(reader, listType);
            if (loadedList != null) {
                artistList.addAll(loadedList);
            }
        } catch (FileNotFoundException e) {
            // Si el archivo no existe, la lista queda vacía
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
