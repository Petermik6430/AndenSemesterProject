package controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import db.BookingDB;
import db.BookingDBIF;
import db.DataAccessException;
import model.Booking;
import model.Customer;
import model.Employee;
import model.Service;

public class BookingController {
	private CustomerController cc;
	private EmployeeController ec;
	private ServiceController sc;
	private BookingDBIF bookingDB;
	public Booking bookingInSystem;
	
	private final LocalTime startOfDayWeekDays = LocalTime.of(9, 0);
	private final LocalTime endOfDatWeekDays = LocalTime.of(18, 0);
	private final LocalTime startOfDaySaturDay = LocalTime.of(10, 0);
	private final LocalTime endOfDaySaturDay = LocalTime.of(15, 0);

	public BookingController() throws DataAccessException {
		cc = new CustomerController();
		ec = new EmployeeController();
		sc = new ServiceController();
		bookingDB = new BookingDB();
	}

	public Booking createBooking() {
		Booking boo = new Booking();
		return null;
	}

	public void createBookingUnit() {
	}

	public void createBookingDate() {
	}

	public void setService(Service service) {
	}

	public Employee findEmployeeById(int employeeId) {
		return null; //TODO skal returnere Employee
	}

	public void setDateTime(LocalDateTime date, LocalTime time) {
	}

	public void createCustomer() {
	}

	public Customer selectCustomer(String phoneNo) {
		return null; //TODO skal returnere Customer
	}

	public void cancelBooking(int bookingId) {
	}


	public List<LocalTime> findAvailableTimes(LocalDate date, Service duration ) throws DataAccessException {
		List<LocalTime> avaliableTimesList = null;
		//tjekker om datoen er søndag
		if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			System.out.println("Ikke muligt at book en søndag" + date);
		} 
		
		List<Booking> boo = bookingDB.findBookingByDate(bookingInSystem.getBookingDate().toLocalDate());
		return avaliableTimesList;
	

	}
}

