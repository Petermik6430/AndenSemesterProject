package controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import db.BookingDB;
import db.BookingDBIF;
import db.DataAccessException;
import model.Customer;
import model.Employee;
import model.Service;

public class BookingController {
	private CustomerController cc;
	private EmployeeController ec;
	private ServiceController sc;
	private BookingDBIF bookingDB;

	public BookingController() throws DataAccessException {
		cc = new CustomerController();
		ec = new EmployeeController();
		sc = new ServiceController();
		bookingDB = new BookingDB();
	}

	public void createBooking(String service, String date, LocalDateTime localDateTime, Employee e) {
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
