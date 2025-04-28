package Model.EventPackage;

import java.time.LocalDateTime;

public class Availability {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isAvailable;

    public Availability(LocalDateTime startDate, LocalDateTime endDate, boolean isAvailable) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.isAvailable = isAvailable;
    }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
