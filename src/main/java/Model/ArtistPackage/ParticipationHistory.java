package Model.ArtistPackage;

import java.time.LocalDate;

public class ParticipationHistory {
    private String idHistory;
    private Artist artist;
    private String eventName;
    private String role;
    private LocalDate date;


    public ParticipationHistory(String idHistory,Artist artist, String eventName, String role,LocalDate date) {
        this.idHistory = idHistory;
        this.artist = artist;
        this.eventName = eventName;
        this.role = role;
        this.date = date;

    }

    public String getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(String idHistory) {
        this.idHistory = idHistory;
    }

    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
