package Repository;

import Model.AccessPackage.Attendee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class AccessControlRepository {
    private static final String FILE_PATH = "access_control.json";
    private final Gson gson = new Gson();

    public void saveToFile(HashMap<String, List<Attendee>> attendeesByEvent) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(attendeesByEvent, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, List<Attendee>> loadFromFile() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<HashMap<String, List<Attendee>>>() {}.getType();
            HashMap<String, List<Attendee>> map = gson.fromJson(reader, type);
            return map != null ? map : new HashMap<>();
        } catch (FileNotFoundException e) {
            return new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}