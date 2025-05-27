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

    @Override
    public String toString() {
        // Convierte el nombre del enum a un formato más legible
        String name = name().replace('_', ' ').toLowerCase();
        String[] words = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }
        return sb.toString().trim();
    }
}
