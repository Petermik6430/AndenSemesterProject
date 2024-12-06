package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DataAccessException;
import model.Booking;
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
	void testAddServiceToBooking() {
	      Booking booking = new Booking();
	        Service service = new Service();
	        service.setServiceId(5);
	        service.setName("Hårklipning");
	        service.setDuration(20);

	        booking.setService(5);

	        // Bekræft at serviceId er korrekt sat i booking
	        assertEquals(5, booking.getService());

	        // Tilføj servicen til booking objektet
	        booking.setService(5);

	        // Bekræft at service objektet er korrekt sat i booking
	        assertNotNull(booking.getService());
	        assertEquals(service, booking.getService());
	        assertEquals("Hårklipning", booking.getService().getName());
	        assertEquals(20, booking.getService().getDuration());
	}
	




}
