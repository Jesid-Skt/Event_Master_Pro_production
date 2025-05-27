package Services;

import Model.AccessPackage.Attendee;

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

    public String getAllAttendeesAsString(String eventId) {
        List<Attendee> attendees = new ArrayList<>(); // Simulación: no hay lógica de eventos implementada
        if (attendees.isEmpty()) {
            return "No hay asistentes registrados para este evento.";
        }
        StringBuilder sb = new StringBuilder();
        int checkInCount = 0;
        for (Attendee attendee : attendees) {
            sb.append(attendee.toString()).append("\n");
            if (attendee.isCheckedIn()) {
                checkInCount++;
            }
        }
        sb.append("\nTotal de asistentes: ").append(attendees.size());
        sb.append("\nTotal con check-in: ").append(checkInCount);
        return sb.toString();
    }

    public List<Attendee> getAllAttendees(String eventId) {
        Repository.AttendeeRepository attendeeRepository = new Repository.AttendeeRepository();
        return attendeeRepository.loadAll();
    }
}