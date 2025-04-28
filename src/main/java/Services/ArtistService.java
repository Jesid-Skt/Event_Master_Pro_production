package Services;

import Model.ArtistPackage.Artist;
import Model.ArtistPackage.TechnicalFeature;
import Model.ArtistPackage.ParticipationHistory;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ArtistService {

    private final Map<String, Artist> artists = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    // Register a new artist
    public void registerArtist() {
        try {
            System.out.println("\n🔹 Registering a new artist...");

            // Generate a unique ID (UUID)
            String id = UUID.randomUUID().toString().substring(0, 8);

            System.out.print("Enter Artist Name: ");
            String name = scanner.nextLine();

            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Artist name cannot be empty.");
            }

            System.out.print("Enter Artist Contact: ");
            String contact = scanner.nextLine();

            if (contact == null || contact.isEmpty()) {
                throw new IllegalArgumentException("Artist contact cannot be empty.");
            }

            System.out.print("Enter number of technical requirements: ");
            int techCount = Integer.parseInt(scanner.nextLine());

            List<String> requirements = new ArrayList<>();
            for (int i = 0; i < techCount; i++) {
                System.out.print("Enter Technical Requirement " + (i + 1) + ": ");
                String requirement = scanner.nextLine();
                if (requirement != null && !requirement.trim().isEmpty()) {
                    requirements.add(requirement);
                }
            }

            // Crear el artista y agregarlo al Map
            Artist artist = new Artist(id, name, contact);
            artist.setTechnicalRequirements(requirements);
            artists.put(id, artist);

            System.out.println("✅ Artist registered successfully with ID: " + id);
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number format. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    // Modify artist information
    public void modifyArtist() {
        try {
            System.out.println("\n🔹 Modifying an artist...");
            System.out.print("Enter Artist ID to modify: ");
            String id = scanner.nextLine();

            if (!artists.containsKey(id)) {
                System.out.println("❌ Artist not found.");
                return;
            }

            Artist artist = artists.get(id);

            System.out.print("Enter new Artist Name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                artist.setName(name);
            }

            System.out.print("Enter new Contact Information (leave blank to keep current): ");
            String contactInfo = scanner.nextLine();
            if (!contactInfo.isEmpty()) {
                artist.setContactInfo(contactInfo);
            }

            System.out.println("✅ Artist updated successfully!");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    // Delete an artist
    public void deleteArtist() {
        try {
            System.out.println("\n🔹 Deleting an artist...");
            System.out.print("Enter Artist ID to delete: ");
            String id = scanner.nextLine();

            if (artists.remove(id) != null) {
                System.out.println("✅ Artist deleted successfully!");
            } else {
                System.out.println("❌ Artist not found.");
            }
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    // Add participation history to an artist
    public void addParticipationHistory() {
        try {
            System.out.println("\n🔹 Adding participation history...");
            System.out.print("Enter Artist ID: ");
            String artistId = scanner.nextLine();

            if (!artists.containsKey(artistId)) {
                System.out.println("❌ Artist not found.");
                return;
            }

            Artist artist = artists.get(artistId);

            System.out.print("Enter Participation History ID: ");
            String historyId = scanner.nextLine();

            System.out.print("Enter Event Name: ");
            String eventName = scanner.nextLine();

            System.out.print("Enter Role (e.g., Speaker, Singer): ");
            String role = scanner.nextLine();

            System.out.print("Enter Participation Date (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateInput);

            ParticipationHistory participation = new ParticipationHistory(historyId, artist, eventName, role, date);
            artist.addParticipationHistory(participation);

            System.out.println("✅ Participation history added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Please enter correct values.");
            scanner.nextLine(); // limpiar buffer
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }
    public void showArtistDetails() {
        try {
            System.out.println("\n🎭 Show Artist Details");
            System.out.print("Enter Artist ID: ");
            String id = scanner.nextLine();

            Artist artist = artists.get(id);
            if (artist == null) {
                System.out.println("❌ Artist not found.");
                return;
            }

            System.out.println("\n📌 Artist Details:");
            System.out.println("ID: " + artist.getIdArtist());
            System.out.println("Name: " + artist.getName());
            System.out.println("Contact Info: " + artist.getContactInfo());

            // Mostrar requisitos técnicos
            if (!artist.getTechnicalRequirements().isEmpty()) {
                System.out.println("\nTechnical Requirements:");
                for (String req : artist.getTechnicalRequirements()) {
                    System.out.println("- " + req);
                }
            }

            // Mostrar historial de participación
            List<ParticipationHistory> history = artist.getParticipationHistories();
            if (!history.isEmpty()) {
                System.out.println("\nParticipation History:");
                for (ParticipationHistory participation : history) {
                    System.out.println("- Event: " + participation.getEventName() +
                            ", Role: " + participation.getRole() +
                            ", Date: " + participation.getDate());
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void showArtistContactInfo() {
        try {
            System.out.println("\n🔹 Showing contact information...");
            System.out.print("Enter Artist ID: ");
            String id = scanner.nextLine();

            Artist artist = artists.get(id);
            if (artist == null) {
                System.out.println("❌ Artist not found.");
                return;
            }

            System.out.println("\n📋 Contact Information:");
            System.out.println("Name: " + artist.getName());
            System.out.println("Contact: " + artist.getContactInfo());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public void showTechnicalRequirements() {
        try {
            System.out.println("\n🔹 Showing technical requirements...");
            System.out.print("Enter Artist ID: ");
            String id = scanner.nextLine();

            Artist artist = artists.get(id);
            if (artist == null) {
                System.out.println("❌ Artist not found.");
                return;
            }

            List<TechnicalFeature> requirements = artist.getTechnicalRequirements().stream()
                    .map(TechnicalFeature::new)
                    .collect(Collectors.toList());
            if (requirements.isEmpty()) {
                System.out.println("❗ No technical requirements registered.");
                return;
            }

            System.out.println("\n📋 Technical Requirements:");
            for (int i = 0; i < requirements.size(); i++) {
                System.out.println((i + 1) + ". " + requirements.get(i).getRequirement());
            }
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    // List all artists
    public void listArtists() {
        try {
            System.out.println("\n📋 List of Artists:");
            if (artists.isEmpty()) {
                System.out.println("❌ No artists registered yet.");
                return;
            }

            for (Artist artist : artists.values()) {
                System.out.println("\n🎭 Artist ID: " + artist.getIdArtist());
                System.out.println("Name: " + artist.getName());
                System.out.println("Contact: " + artist.getContactInfo());
                if (!artist.getTechnicalRequirements().isEmpty()) {
                    System.out.println("Technical Requirements: " + artist.getTechnicalRequirements());
                }
            }
            System.out.println("\nTotal artists: " + artists.size());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    // Get artist by ID
    public Artist getArtistById(String id) {
        return artists.get(id);
    }

    // Getter for artists (for other services)
    public Map<String, Artist> getArtists() {
        return artists;
    }
}

