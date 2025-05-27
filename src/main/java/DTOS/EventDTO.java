// src/main/java/DTOS/EventDTO.java
        package DTOS;

        public class EventDTO {
            private String eventId;
            private String venueId;
            private String eventName;
            private String eventType;
            private String startDate;
            private String endDate;

            public EventDTO() {}

            public EventDTO(String eventId, String eventName, String eventType, String startDate, String endDate, String venueId) {
                // Asigna los valores a los campos correspondientes
                this.eventId = eventId;
                this.eventName = eventName;
                this.eventType = eventType;
                this.startDate = startDate;
                this.endDate = endDate;
                this.venueId = venueId;
            }

            public String getEventId() {
                return eventId;
            }

            public void setEventId(String eventId) {
                this.eventId = eventId;
            }

            public String getVenueId() {
                return venueId;
            }

            public void setVenueId(String venueId) {
                this.venueId = venueId;
            }

            public String getEventName() {
                return eventName;
            }

            public void setEventName(String eventName) {
                this.eventName = eventName;
            }

            public String getEventType() {
                return eventType;
            }

            public void setEventType(String eventType) {
                this.eventType = eventType;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            @Override
            public String toString() {
                return eventName;
            }
        }