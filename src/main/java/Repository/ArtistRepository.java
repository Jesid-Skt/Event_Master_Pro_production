package Repository;

import DTOS.ArtistDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository {
    private final String FILE_PATH = "artists.json";
    private List<ArtistDTO> artistList = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public void addArtist(ArtistDTO artist) {
        artistList.add(artist);
        saveToFile();
    }

    public ArtistDTO findById(String id) {
        return artistList.stream()
                .filter(a -> a.getArtistId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<ArtistDTO> getAllArtists() {
        return artistList;
    }

    public void saveToFile() {
        try {
            mapper.writeValue(new File(FILE_PATH), artistList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                artistList = mapper.readValue(file, new TypeReference<List<ArtistDTO>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
