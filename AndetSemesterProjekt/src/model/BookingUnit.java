package model;

import java.time.LocalTime;

public class BookingUnit {
    private LocalTime time;
    private BookingType status;

    public BookingUnit(LocalTime time, BookingType status) {
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
