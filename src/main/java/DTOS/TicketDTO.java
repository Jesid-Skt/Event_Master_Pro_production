package DTOS;

import Enums.TicketType;
import Model.EventPackage.Event;

public class TicketDTO {
    private String code;
    private TicketType type;
    private double price;
    private boolean isSold;
    private boolean isUsed;
    private Event event;

    public TicketDTO() {}

    public TicketDTO(String code, TicketType type, double price, boolean isSold, boolean isUsed, Event event) {
        this.code = code;
        this.type = type;
        this.price = price;
        this.isSold = isSold;
        this.isUsed = isUsed;
        this.event = event;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "TikectDTO{" +
                "code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", isSold=" + isSold +
                ", isUsed=" + isUsed +
                ", event=" + (event != null ? event.toString() : "null") +
                '}';
    }
}