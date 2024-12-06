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
		
		bookingInSystem = boo;
		return boo;
	}

//	public void createBookingUnit() {
//	}

//	public void createBookingDate() {
//	}

	public void setService(int serviceId) {
		bookingInSystem.setService(serviceId);
		
	}
	public void setNote(String note) {
		bookingInSystem.setNote(note);
	}
	
	public void setEmployee(Employee employee) {
		bookingInSystem.setEmployee(employee);
		
		 //TODO skal returnere Employee
	}

	public List<LocalDateTime> setDateTime(LocalDate date) {
		List<LocalDateTime> boo = new ArrayList<>();
		
		bookingInSystem.setBookingDate(LocalDateTime.of(date, LocalTime.MIN));
	
		
		
		return boo;
	}

//	public int createCustomer() {
//		return 1;
//	}

	public Customer selectCustomer(String phoneNo) throws DataAccessException {
		Customer customer = cc.findCustomerByPhoneNo(phoneNo);
		
		// håndtering hvis kunden ikke er i systemet.
		/* if(customer == null) {
			cc.createCustomer(0, phoneNo, phoneNo, phoneNo, phoneNo);
		}
	*/	
		bookingInSystem.setCustomer(customer);
		return customer; //TODO skal returnere Customer
	}

	//public void cancelBooking(int bookingId) {
//	}


	public List<LocalTime> findAvailableTimes() throws DataAccessException {
		List<LocalTime> avaliableTimesList = null;
		//tjekker om datoen er søndag
		//if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
		//	System.out.println("Ikke muligt at book en søndag" + date);
		//} 
		
		List<Booking> boo = bookingDB.findBookingByDate(bookingInSystem.getBookingDate().toLocalDate());
		return avaliableTimesList;
	

	}
}// map til hver employee og tilknytte en liste af booking.

