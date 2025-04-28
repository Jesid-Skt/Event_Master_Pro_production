package Model.ArtistPackage;


import Model.EventPackage.Event;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String idArtist;
    private String name;
    private String contactInfo;
    private List<String> technicalRequirements;
    private List<Event> eventHistory;
    private List<ParticipationHistory> participationHistories;

    public Artist(String idArtist, String name, String contactInfo) {
        this.idArtist = idArtist;
        this.name = name;
        this.contactInfo = contactInfo;
        this.technicalRequirements = new ArrayList<>();
        this.eventHistory = new ArrayList<>();
        this.participationHistories = new ArrayList<>();
    }

    public String getIdArtist() { return idArtist; }
    public void setIdArtist(String idArtist) { this.idArtist = idArtist; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public List<String> getTechnicalRequirements() { return technicalRequirements; }
    public void setTechnicalRequirements(List<String> technicalRequirements) { this.technicalRequirements = technicalRequirements; }

    public List<Event> getEventHistory() { return eventHistory; }
    public void setEventHistory(List<Event> eventHistory) { this.eventHistory = eventHistory; }

    public List<ParticipationHistory> getParticipationHistories() {
        return participationHistories;
    }

    public void setParticipationHistories(List<ParticipationHistory> participationHistories) {
        this.participationHistories = participationHistories;
    }

    public void addParticipationHistory(ParticipationHistory participation) {
        this.participationHistories.add(participation);
    }

}
