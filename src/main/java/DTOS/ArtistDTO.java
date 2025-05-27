package DTOS;

    import java.util.List;

    public class ArtistDTO {
        private String idArtist;
        private String name;
        private String contactInfo;
        private List<String> technicalRequirements;
        private List<String> eventHistory; // Usualmente se usan IDs o nombres, no objetos completos
        private List<String> participationHistories; // Igual, IDs o descripciones

        public ArtistDTO() {}

        public ArtistDTO(String idArtist, String name, String contactInfo,
                         List<String> technicalRequirements,
                         List<String> eventHistory,
                         List<String> participationHistories) {
            this.idArtist = idArtist;
            this.name = name;
            this.contactInfo = contactInfo;
            this.technicalRequirements = technicalRequirements;
            this.eventHistory = eventHistory;
            this.participationHistories = participationHistories;
        }

        // Getters y setters para todos los campos...


        public String getIdArtist() {
            return idArtist;
        }

        public void setIdArtist(String idArtist) {
            this.idArtist = idArtist;
        }

        public String getNameArtist() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContactInfo() {
            return contactInfo;
        }

        public void setContactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
        }

        public List<String> getTechnicalRequirements() {
            return technicalRequirements;
        }

        public void setTechnicalRequirements(List<String> technicalRequirements) {
            this.technicalRequirements = technicalRequirements;
        }

        public List<String> getEventHistory() {
            return eventHistory;
        }

        public void setEventHistory(List<String> eventHistory) {
            this.eventHistory = eventHistory;
        }

        public List<String> getParticipationHistories() {
            return participationHistories;
        }

        public void setParticipationHistories(List<String> participationHistories) {
            this.participationHistories = participationHistories;
        }

        @Override
        public String toString() {
            return "ArtistDTO{" +
                    "idArtist='" + idArtist + '\'' +
                    ", name='" + name + '\'' +
                    ", contactInfo='" + contactInfo + '\'' +
                    ", technicalRequirements=" + technicalRequirements +
                    ", eventHistory=" + eventHistory +
                    ", participationHistories=" + participationHistories +
                    '}';
        }
    }