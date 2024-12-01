package controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import db.BookingDBIF;
import model.Employee;
import model.Service;

public class BookingController {
	private CustomerController cc;
	private EmployeeController ec;
	private ServiceController sc;
	private BookingDBIF bookingDB;

	public BookingController() {
	}

	public void createBooking(String serviceType, String date, LocalDateTime localDateTime, Employee e) {
	}

	public void createBookingUnit() {
	}

	public void createBookingDate() {
	}

	public void setService(Service service) {
	}

	public Employee findEmployeeById(int employeeId) {
		return null;
	}

	public void setDateTime(LocalDateTime date, LocalTime time) {
	}

	public void createCustomer() {
	}

	public Customer selectCustomer(String phoneNo) {
		return null;
	}

	public void cancelBooking(int bookingId) {
	}

	public List<LocalTime> findAvailableTimes() {
		return null;
	}
}
