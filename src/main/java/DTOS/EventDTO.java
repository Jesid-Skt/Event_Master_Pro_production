// src/main/java/DTOS/EventDTO.java
        package DTOS;

import Model.EventPackage.Venue;

public class EventDTO {
            private String eventId;
            private String venueId;
            private String eventName;
            private String eventType;
            private String startDate;
            private String endDate;
            private VenueDTO venue; // Asignar el objeto VenueDTO

            public EventDTO() {}

            public EventDTO(String eventId, String eventName, String eventType, String startDate, String endDate, VenueDTO venue) {
                this.eventId = eventId;
                this.eventName = eventName;
                this.eventType = eventType;
                this.startDate = startDate;
                this.endDate = endDate;
                this.venue = venue; // Asignar el objeto Venue a partir de VenueDTO

            }

            public VenueDTO getVenue() {
                return venue;
            }
            public void setVenue(VenueDTO venue) {
                this.venue = venue; // Asignar el objeto Venue a partir de VenueDTO
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