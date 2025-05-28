package Repository;

import Model.ArtistPackage.TechnicalFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TechnicalFeatureRepository {
    private static final String FILE_PATH = "technical_features.json";
    private final Gson gson = new Gson();
    private List<TechnicalFeature> technicalFeatures = new ArrayList<>();

    public void addTechnicalFeature(TechnicalFeature feature) {
        technicalFeatures.add(feature);
        saveToFile();
    }

    public List<TechnicalFeature> getAllTechnicalFeatures() {
        return new ArrayList<>(technicalFeatures);
    }

    public void saveToFile() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(technicalFeatures, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        technicalFeatures.clear();
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<TechnicalFeature>>() {}.getType();
            List<TechnicalFeature> loadedList = gson.fromJson(reader, listType);
            if (loadedList != null) {
                technicalFeatures.addAll(loadedList);
            }
        } catch (FileNotFoundException e) {
            // Si el archivo no existe, la lista queda vacía
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}