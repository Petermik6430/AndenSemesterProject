package db;

import java.time.LocalDate;
import java.util.List;

import model.Booking;

public interface BookingDBIF {
	
	public int createBooking(Booking booking) throws  DataAccessException;

	public List<Booking> findBookingByDate(LocalDate date) throws DataAccessException;
	
	public Booking findBookingByCustomerPhoneNo(String phoneNo) throws DataAccessException;

	public void updateBooking(Booking booking) throws DataAccessException;

	public void deleteBooking(int bookingId) throws DataAccessException;
	
	//public List<Booking> updateBookingCalender(LocalDate date) throws DataAccessException;

}
