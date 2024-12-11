package db;

import java.time.LocalDate;
import java.util.List;

import model.Booking;

public interface BookingDBIF {
	
	public int createBooking(Booking booking) throws  DataAccessException;

	public List<Booking> findBookingByDate(LocalDate date) throws DataAccessException;

}