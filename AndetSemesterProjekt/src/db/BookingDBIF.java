package db;

import java.sql.SQLException;

import model.Booking;

public interface BookingDBIF {
	
	public void createBooking(Booking booking) throws  DataAccessException;

	public Booking findBookingByCustomerPhoneNo(String phoneNo) throws DataAccessException;

	public void updateBooking(Booking booking) throws DataAccessException;

	public void deleteBooking(int bookingId) throws DataAccessException;

}
