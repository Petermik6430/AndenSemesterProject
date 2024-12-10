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
	//	bookingInSystem = new Booking();
	//	Booking boo = new Booking();
		employeeBookings = new HashMap<>();
	//	createBooking();
	}

public Booking createBooking(Booking booking) {
		booking = new Booking();
		
		bookingInSystem = booking;
		try {
			bookingDB.createBooking(booking);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booking;
	}



/*
	public void createBooking(Booking booking) throws DataAccessException {
		if (booking.getEmployee() == null) {
			throw new DataAccessException("Employee cannot be null", null);
		}
	
}
*/
/*
public void createBooking(Employee employee, LocalDate date, LocalTime time) throws DataAccessException {
	
	Customer customer = cc.findCustomerByPhoneNo("12345678"); // Eksempel telefonnummer, opdater som nødvendigt
    if (customer == null) {
        throw new DataAccessException("Customer is null in BookingController",null);
    }
    Service service = new Service();
    service.setServiceId(1);

    Booking booking = new Booking(employee, date.atTime(time), BookingType.booked);
    booking.setCustomer(customer);
    booking.setService(service);
    booking.setNote("Test booking");

    System.out.println("Customer ID: " + customer.getCustomerId());
    System.out.println("Service ID: " + service.getServiceId());
    System.out.println("Booking Date: " + booking.getBookingDate());
    System.out.println("Employee ID: " + employee.getEmployeeId());

    bookingDB.createBooking(booking);
}
*/


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

//	public int createCustomer() {
//		return 1;
//	}
	
	public Customer selectCustomer(String phoneNo) throws DataAccessException {
		Customer res = null;
		res = cc.findCustomerByPhoneNo(phoneNo);
		
		return res;
	}
	
/*
	public Customer selectCustomer(String phoneNo) throws DataAccessException {
		Customer customer = cc.findCustomerByPhoneNo(phoneNo);
		
		// håndtering hvis kunden ikke er i systemet.
		/* if(customer == null) {
			cc.createCustomer(0, phoneNo, phoneNo, phoneNo, phoneNo);
		}
		
		bookingInSystem.setCustomer(customer);
		return customer; //TODO skal returnere Customer
	}
*/
	
	public void addBooking(Employee employee, Booking booking) {
		if(!employeeBookings.containsKey(employee)) { // vi kunne bruge en lamdaudtryk her
			employeeBookings.put(employee, new ArrayList<>());
		}
		employeeBookings.get(employee).add(booking);
		
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

