package model;

import java.time.LocalTime;

public class TimeSlot {
    private LocalTime time;
    private BookingType status;

    public TimeSlot(LocalTime time, BookingType status) {
        this.time = time;
        this.status = status;
    }

    public LocalTime getTime() {
        return time;
    }

    public BookingType getStatus() {
        return status;
    }
}
