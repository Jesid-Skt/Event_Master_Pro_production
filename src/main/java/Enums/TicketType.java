package Enums;

public enum TicketType {
    GENERAL_ADMISSION(30000),
    VIP(50000),
    EARLY_ACCESS(25000),
    RESERVED_SEATING(15000),
    BACKSTAGE_PASS(70000),
    STUDENT(10000),
    SENIOR(10000),
    CHILD(5000);

    private final double price;

    private TicketType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
