package Repository;

import Model.AccessPackage.Attendee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AttendeeRepository {
    private static final String FILE_PATH = "attendees.json";
    private final Gson gson = new Gson();

    public void saveAll(List<Attendee> attendees) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(attendees, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Attendee> loadAll() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<List<Attendee>>() {}.getType();
            List<Attendee> attendees = gson.fromJson(reader, type);
            return attendees != null ? attendees : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}