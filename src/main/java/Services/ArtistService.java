package Services;

import Model.ArtistPackage.Artist;
import Model.ArtistPackage.TechnicalFeature;
import Model.ArtistPackage.ParticipationHistory;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ArtistService {

    private final Map<String, Artist> artists = new HashMap<>();

    public Artist registerArtist(String name, String contact, List<String> requirements) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Artist name cannot be empty.");
        }
        if (contact == null || contact.isEmpty()) {
            throw new IllegalArgumentException("Artist contact cannot be empty.");
        }

        String id = UUID.randomUUID().toString().substring(0, 8);
        Artist artist = new Artist(id, name, contact);
        artist.setTechnicalRequirements(requirements);
        artists.put(id, artist);
        return artist;
    }

    public boolean modifyArtist(String id, String newName, String newContact) {
        if (!artists.containsKey(id)) return false;

        Artist artist = artists.get(id);
        if (newName != null && !newName.isEmpty()) {
            artist.setName(newName);
        }
        if (newContact != null && !newContact.isEmpty()) {
            artist.setContactInfo(newContact);
        }
        return true;
    }

    public boolean deleteArtist(String id) {
        return artists.remove(id) != null;
    }

    public boolean addParticipationHistory(String artistId, String historyId, String eventName, String role, LocalDate date) {
        if (!artists.containsKey(artistId)) return false;

        Artist artist = artists.get(artistId);
        ParticipationHistory participation = new ParticipationHistory(historyId, artist, eventName, role, date);
        artist.addParticipationHistory(participation);
        return true;
    }

    public String getArtistDetails(String id) {
        Artist artist = artists.get(id);
        if (artist == null) return "❌ Artist not found.";

        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(artist.getIdArtist()).append("\n")
                .append("Name: ").append(artist.getName()).append("\n")
                .append("Contact Info: ").append(artist.getContactInfo()).append("\n\n");

        if (!artist.getTechnicalRequirements().isEmpty()) {
            sb.append("Technical Requirements:\n");
            for (String req : artist.getTechnicalRequirements()) {
                sb.append("- ").append(req).append("\n");
            }
        }

        List<ParticipationHistory> history = artist.getParticipationHistories();
        if (!history.isEmpty()) {
            sb.append("\nParticipation History:\n");
            for (ParticipationHistory participation : history) {
                sb.append("- Event: ").append(participation.getEventName())
                        .append(", Role: ").append(participation.getRole())
                        .append(", Date: ").append(participation.getDate()).append("\n");
            }
        }
        return sb.toString();
    }

    public String getArtistContactInfo(String id) {
        Artist artist = artists.get(id);
        if (artist == null) return "❌ Artist not found.";
        return "Name: " + artist.getName() + "\nContact: " + artist.getContactInfo();
    }

    public List<TechnicalFeature> getTechnicalRequirements(String id) {
        Artist artist = artists.get(id);
        if (artist == null) return Collections.emptyList();
        return artist.getTechnicalRequirements().stream()
                .map(TechnicalFeature::new)
                .collect(Collectors.toList());
    }

    public List<Artist> getAllArtists() {
        return new ArrayList<>(artists.values());
    }

    public Artist getArtistById(String id) {
        return artists.get(id);
    }

    public Map<String, Artist> getArtists() {
        return artists;
    }
}

