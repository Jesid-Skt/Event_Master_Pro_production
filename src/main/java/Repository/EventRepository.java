// src/main/java/Repository/EventRepository.java
package Repository;

import DTOS.EventDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EventRepository {
    private final Map<String, EventDTO> allEvents = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath = "events.json";

    public EventRepository() {
        loadFromFile();
    }

    // Agrega un evento y guarda en archivo
    public void addEvent(EventDTO event) {
        allEvents.put(event.getEventId(), event);
        saveToFile();
    }

    // Actualiza un evento existente y guarda
    public void updateEvent(EventDTO event) {
        if (allEvents.containsKey(event.getEventId())) {
            allEvents.put(event.getEventId(), event);
            saveToFile();
        }
    }

    // Obtiene un evento por ID
    public EventDTO getEventById(String id) {
        return allEvents.get(id);
    }

    // Busca eventos por nombre (puede haber varios con el mismo nombre)
    public List<EventDTO> findEventsByName(String name) {
        List<EventDTO> result = new ArrayList<>();
        for (EventDTO event : allEvents.values()) {
            if (event.getEventName().equalsIgnoreCase(name)) {
                result.add(event);
            }
        }
        return result;
    }

    // Devuelve todos los eventos como lista
    public List<EventDTO> getAllEventsAsList() {
        return new ArrayList<>(allEvents.values());
    }

    // Devuelve todos los eventos como mapa
    public Map<String, EventDTO> getAllEvents() {
        return allEvents;
    }

    // Sobrescribe todos los eventos y guarda
    public void setAllEvents(List<EventDTO> events) {
        allEvents.clear();
        for (EventDTO event : events) {
            allEvents.put(event.getEventId(), event);
        }
        saveToFile();
    }

    // Elimina un evento por ID y guarda
    public void removeEventById(String id) {
        allEvents.remove(id);
        saveToFile();
    }

    // Guarda los eventos en archivo JSON
    public void saveToFile() {
        try {
            objectMapper.writeValue(new File(filePath), allEvents.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carga los eventos desde archivo JSON
    public void loadFromFile() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                List<EventDTO> events = objectMapper.readValue(file, new TypeReference<List<EventDTO>>() {});
                allEvents.clear();
                for (EventDTO event : events) {
                    allEvents.put(event.getEventId(), event);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}