package Model.TicketPackage;


import Enums.TicketType;
import Model.EventPackage.Event;


public class Ticket {
    private String code;
    private TicketType type;
    private double price;
    private boolean isSold;
    private boolean isUsed;
    private Event event;

    public Ticket(String code, TicketType type, double price, Event event) {
        this.code = code;
        this.type = type;
        this.price = price;
        this.event = event;
        this.isSold = false;
        this.isUsed = false;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public TicketType getType() { return type; }
    public void setType(TicketType type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isSold() { return isSold; }
    public void setSold(boolean sold) { isSold = sold; }

    public boolean isUsed() { return isUsed; }
    public void setUsed(boolean used) { isUsed = used; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
}
