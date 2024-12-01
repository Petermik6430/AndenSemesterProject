package db;

import java.time.LocalDateTime;
import model.Booking;

public interface BookingDBIF {
	void createBooking(String trimType, String bookingDate, LocalDateTime localDateTime, String barber);

	Booking findBookingByCustomerPhoneNo(String phoneNo);

	void updateBooking(Booking booking);

	void deleteBooking(int bookingId);

}
