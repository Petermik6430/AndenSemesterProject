package db;

//BookingDB.java
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import model.Booking;

public class BookingDB implements BookingDBIF {
	private DBConnection dbc;
	private static final String FIND_BY_BOOKING_ID_SQL = "FIND_BY_BOOKING_ID_SQL";
	private static final String FIND_BY_BOOKING_BY_DATE_SQL = "FIND_BY_BOOKING_BY_DATE_SQL";
	private PreparedStatement ps_findByBookingId;
	private PreparedStatement ps_findByBookingDate;

	@Override
	public void createBooking(int bookingId, String serviceType, String bookingDate, LocalDateTime localDateTime,
			String barber) {
		// Method implementation
	}

	@Override
	public Booking findBookingByCustomerPhoneNo(String phoneNo) {
		// Method implementation
		return null;
	}

	@Override
	public void updateBooking(Booking booking) {
		// Method implementation
	}

	@Override
	public void deleteBooking(int bookingId) {
		// Method implementation
	}

	@Override
	public void createBooking(String trimType, String bookingDate, LocalDateTime localDateTime, String barber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBooking(Booking booking) {
		// TODO Auto-generated method stub

	}
}
