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
	private BookingDBIF bookingDB;
	public Booking bookingInSystem;
	private Map <Employee, List<Booking>> employeeBookings;
	
	private final LocalTime startOfDayWeekDays = LocalTime.of(9, 0);
	private final LocalTime endOfDatWeekDays = LocalTime.of(18, 0);
	private final LocalTime startOfDaySaturDay = LocalTime.of(10, 0);
	private final LocalTime endOfDaySaturDay = LocalTime.of(15, 0);

	public BookingController() throws DataAccessException {
		cc = new CustomerController();
		ec = new EmployeeController();
		sc = new ServiceController();
		bookingDB = new BookingDB();
		bookingInSystem = new Booking();
	//	Booking boo = new Booking();
		employeeBookings = new HashMap<>();
	//	createBookings();
	}

	//public Booking createBookings() {
	//	Booking boo = new Booking();
		
	//	bookingInSystem = boo;
	//	return boo;
	//}

	public void createBooking(Booking booking) throws DataAccessException {
		if (booking.getEmployee() == null) {
			throw new DataAccessException("Employee cannot be null", null);
		}
		
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
		List<LocalTime> times = new ArrayList<>();
		
		bookingInSystem.setBookingDate(LocalDateTime.of(date, LocalTime.MIN));
	
		
		
		return times;
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
	
	public void addBooking(Employee employee, Booking booking) {
		if(!employeeBookings.containsKey(employee)) { // vi kunne bruge en lamdaudtryk her
			employeeBookings.put(employee, new ArrayList<>());
		}
		employeeBookings.get(employee).add(booking);
		
	}
	
	public List<TimeSlot> findAvailableTimes(Employee employee, LocalDate date) throws DataAccessException {
	    if (employee == null) {
	        throw new DataAccessException("Employee cannot be null in booking", null);
	    }

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
	                    break;
	                }
	            }
	        }
	        timeSlots.add(new TimeSlot(time, status));
	    }

	    return timeSlots;
	}

	



	
}// map til hver employee og tilknytte en liste af booking.

