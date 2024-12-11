package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DataAccessException;
import model.Booking;
import model.BookingType;
import model.Service;

class BookingIntegrationTest {

	private static BookingController bookingController;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		try {
	bookingController = new BookingController();
		} catch (DataAccessException e) {
			fail("Der skete fejl her");
		}
		} 



	@Test
	void testCreateBooking() {
		
	Booking created;
	Booking arranged = new Booking();
	
	created = bookingController.createBooking();
		assertEquals(created, arranged);
	}
	
	
	@Test
	void testAddDate() {
		bookingController.createBooking();
		LocalDate testDate = LocalDate.of(2018, 6, 15);
		List<LocalTime> expected = new ArrayList<>();
		int duration = 15;
		LocalTime startTime = LocalTime.of(16, 0);
		 while(startTime.isBefore(LocalTime.of(18, 00))) {
			 expected.add(startTime);
			 startTime = startTime.plusMinutes(duration);
		 }
		 
		 List<LocalTime> timeReturned = bookingController.setDateTime(testDate);
		 
		 for(LocalTime localTime : timeReturned) {
			 assertEquals(localTime, expected.get(timeReturned.indexOf(localTime)));
		 }
	}
	
	@Test
	void testAddServiceToBooking() {
	      	Booking booking = new Booking();
	        Service service = new Service();
	        service.setServiceId(5);
	        service.setName("Hårklipning");
	        service.setDuration(20);

	        booking.setService(service);

	        // Bekræft at serviceId er korrekt sat i booking
	        assertEquals(service, booking.getService());

	 
	        // Tilføj servicen til booking objektet
	        assertEquals(5, booking.getService().getServiceId());

	        // Bekræft at service objektet er korrekt sat i booking
	        assertNotNull(booking.getService());
	        assertEquals(service, booking.getService());
	        assertEquals("Hårklipning", booking.getService().getName());
	        assertEquals(20, booking.getService().getDuration());
	}
	




}
