package Repository;

import DTOS.EventDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRepository {

    private Map<String, EventDTO> eventMap = new HashMap<>();
    private List<EventDTO> eventList;
    private final String FILE_PATH = "eventos.json";
    private final Gson gson = new Gson();

    public void addEvent(EventDTO event) {
        eventMap.put(event.getEventId(), event);
        saveToFile();
    }

    public EventDTO findById(String id) {
        return eventMap.get(id);
    }

    public Map<String, EventDTO> getAllEvents() {
        return eventMap;
    }

    public void saveToFile() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(eventMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<Map<String, EventDTO>>() {}.getType();
            eventMap = gson.fromJson(reader, type);
            if (eventMap == null) {
                eventMap = new HashMap<>();
            }
        } catch (FileNotFoundException e) {
            eventMap = new HashMap<>(); // Si no existe, lo inicializa vacío
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAllEvents(List<EventDTO> events) {
        this.eventList = events;
    }
}

