package Services;

import java.util.*;

public class AttendeeService {
    private static final Map<String, Boolean> attendees = new HashMap<>();

    // Genera un ID único para el asistente
    public static String generateAttendeeID() {
        return "ATTENDEE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Registra un nuevo asistente
    public static String registerNewAttendee() {
        String id = generateAttendeeID();
        attendees.put(id, false); // false = no ha ingresado aún
        return id;
    }

    // Valida la entrada de un asistente por su ID
    public static boolean validateEntry(String attendeeId) {
        if (attendees.containsKey(attendeeId)) {
            attendees.put(attendeeId, true); // Marca como ingresado
            return true;
        }
        return false;
    }

    // Muestra estadísticas básicas de asistencia
    public static String showAttendanceStatistics() {
        int total = attendees.size();
        long attended = attendees.values().stream().filter(v -> v).count();
        return "Total registrados: " + total + "\nAsistieron: " + attended + "\nNo asistieron: " + (total - attended);
    }
}