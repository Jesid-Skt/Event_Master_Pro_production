package DTOS;

import Enums.EventType;

public class EventDTO {
        private String eventID;
        private String venueID;
        private String event_name;
        private String event_type;
        private String startDate;
        private String endDate;

        public EventDTO() {
        }

    public EventDTO(String event_name, String event_type, String startDate, String endDate, String venueID) {
        this.event_name = event_name;
        this.event_type = event_type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venueID = venueID;
    }

        public String getEventId() {
            return eventID;
        }

        public void setEventId(String eventId) {
            this.eventID = eventId;
        }

        public String getVenueId() {
            return venueID;
        }

        public void setVenueId(String venueId) {
            this.venueID = venueId;
        }

        public String getEventName() {
            return event_name;
        }

        public void setEventName(String name) {
            this.event_name = name;
        }

        public String getEventType() {
            return event_type;
        }

        public void setEventType(String type) {
            this.event_type = type;
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
            return "EventDTO{" +
                    "eventId='" + eventID + '\'' +
                    ", venueId='" + venueID + '\'' +
                    ", name='" + event_name + '\'' +
                    ", type='" + event_type + '\'' +
                    ", startDate='" + startDate + '\'' +
                    ", endDate='" + endDate + '\'' +
                    '}';
        }
    }


