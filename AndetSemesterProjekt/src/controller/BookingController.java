package controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import db.BookingDB;
import db.BookingDBIF;
import db.DataAccessException;
import model.Booking;
import model.BookingType;
import model.Customer;
import model.Employee;
import model.Service;
import model.TimeSlot;

public class BookingController {
	private CustomerController cc;
	private EmployeeController ec;
	private ServiceController sc;
	private BookingDB bookingDB;
	public Booking bookingInSystem;
	private Map <Employee, List<Booking>> employeeBookings;
	private Booking booking;
	
	private final LocalTime startOfDayWeekDays = LocalTime.of(9, 0);
	private final LocalTime endOfDatWeekDays = LocalTime.of(18, 0);
	private final LocalTime startOfDaySaturDay = LocalTime.of(10, 0);
	private final LocalTime endOfDaySaturDay = LocalTime.of(15, 0);

	public BookingController() throws DataAccessException {
		cc = new CustomerController();
		ec = new EmployeeController();
		sc = new ServiceController();
		bookingDB = new BookingDB();
		employeeBookings = new HashMap<>();

	}


public Booking createBooking(Booking booking) throws DataAccessException { 
	bookingInSystem = booking; 
	bookingDB.createBooking(booking); 

	return booking;
}



private void updateBookingStatus(Employee employee, LocalDate localDate, LocalTime localTime) {
		// TODO 
		
	}


	public void setService(Service service) {
		bookingInSystem.setService(service);
		
	}

	public void setNote(String note) {
		bookingInSystem.setNote(note);
	}
	
	public void setEmployee(Employee employee) {
		bookingInSystem.setEmployee(employee);
		
		if(!employeeBookings.containsKey(employee)) {
			employeeBookings.put(employee, new ArrayList<>());
		}
		
		 //TODO skal returnere Employee
	}

	public List<LocalTime> setDateTime(LocalDate date) {
		List<LocalTime> res = new ArrayList<>();
		
		bookingInSystem.setBookingDate(LocalDateTime.of(date, LocalTime.MIN));
	
		
		
		return res;
	}


	
	public Customer selectCustomer(String phoneNo) throws DataAccessException {
		Customer res = null;
		res = cc.findCustomerByPhoneNo(phoneNo);
		
		return res;
	}
	
	
	public Map<Employee,List<TimeSlot>> findAvailableTimes( LocalDate date) throws DataAccessException {
		 		
		//TODO 

	    return null;
	}
	public List<TimeSlot> findAvailableTimes(Employee employee, LocalDate date) throws DataAccessException {
	 
	    int employeeId = employee.getEmployeeId();
	    List<TimeSlot> timeSlots = new ArrayList<>();
	    LocalTime startTime = LocalTime.of(9, 0);
	    LocalTime endTime = LocalTime.of(18, 0);

	    List<Booking> bookings = bookingDB.findBookingByDate(date); // Hent bookinger fra databasen

	    for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
	        BookingType status = BookingType.available;
	        for (Booking booking : bookings) {
	            if (booking.getEmployee() != null && booking.getEmployee().getEmployeeId() == employeeId) {
	                LocalTime bookingStart = booking.getBookingDate().toLocalTime();
	                LocalTime bookingEnd = bookingStart.plusMinutes(30);
	                if (!time.isBefore(bookingStart) && time.isBefore(bookingEnd)) {
	                    status = BookingType.booked;
	                }
	            }
	        }
	        timeSlots.add(new TimeSlot(time, status));
	    }

	    return timeSlots;
	}

	
/*
	public List<Booking> findBookingsByDate(LocalDate date) throws DataAccessException {
	    return bookingDB.updateBookingCalender(date);
	}


*/




	



	
}// map til hver employee og tilknytte en liste af booking.

