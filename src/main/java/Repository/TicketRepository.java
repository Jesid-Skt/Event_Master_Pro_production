package Repository;

import DTOS.TicketDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketRepository {

    private Map<String, TicketDTO> ticketMap = new HashMap<>();
    private final String FILE_PATH = "tickets.json";
    private final Gson gson = new Gson();

    public void addTicket(TicketDTO ticket) {
        ticketMap.put(ticket.getCode(), ticket);
        saveToFile();
    }

    public TicketDTO findById(String code) {
        return ticketMap.get(code);
    }

    public Map<String, TicketDTO> getAllTickets() {
        return ticketMap;
    }

    public List<TicketDTO> getAllTicketsAsList() {
        return ticketMap.values().stream().toList();
    }

    public void saveToFile() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(ticketMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<Map<String, TicketDTO>>() {}.getType();
            ticketMap = gson.fromJson(reader, type);
            if (ticketMap == null) {
                ticketMap = new HashMap<>();
            }
        } catch (FileNotFoundException e) {
            ticketMap = new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}