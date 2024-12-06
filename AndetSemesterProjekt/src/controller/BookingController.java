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
		createBooking();
		bookingInSystem = new Booking();
		
		employeeBookings = new HashMap<>();
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
	
	public List<LocalTime> findAvailableTimes(Employee employee, LocalDate date) throws DataAccessException {
	    List<LocalTime> availableTimesList = new ArrayList<>();
	    LocalTime startTime = LocalTime.of(9, 0);
	    LocalTime endTime = LocalTime.of(18, 0);

	    List<Booking> bookings = bookingDB.findBookingByDate(date); // Hent bookinger fra databasen

	    for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
	        boolean isAvailable = true;
	        for (Booking booking : bookings) {
	            LocalTime bookingStart = booking.getBookingDate().toLocalTime();
	            LocalTime bookingEnd = bookingStart.plusMinutes(30); // Antag 30 minutters booking
	            if (!time.isBefore(bookingStart) && time.isBefore(bookingEnd)) {
	                isAvailable = false;
	                break;
	            }
	        }
	        if (isAvailable) {
	            availableTimesList.add(time);
	        }
	    }

	    return availableTimesList;
	}

	
	/*
	public List<LocalTime> findAvailableTimes(Employee employee, LocalDate date) throws DataAccessException {
	    List<LocalTime> availableTimesList = new ArrayList<>();
	    LocalTime startTime = LocalTime.of(9, 0); // Eksempel starttidspunkt
	    LocalTime endTime = LocalTime.of(18, 0); // Eksempel sluttidspunkt

	    List<Booking> bookings = new ArrayList<>();
	    if (employeeBookings.containsKey(employee)) {
	        for (Booking booking : employeeBookings.get(employee)) {
	            if (booking.getBookingDate().toLocalDate().equals(date)) {
	                bookings.add(booking);
	            }
	        }
	    }

	    for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
	        boolean isAvailable = true;
	        for (Booking booking : bookings) {
	            LocalTime bookingStart = booking.getBookingDate().toLocalTime();
	            LocalTime bookingEnd = bookingStart.plusMinutes(30); // Antag 30 minutters booking
	            if (!time.isBefore(bookingStart) && time.isBefore(bookingEnd)) {
	                isAvailable = false;
	                break;
	            }
	        }
	        if (isAvailable) {
	            availableTimesList.add(time);
	        }
	    }

	    return availableTimesList;
	}
	*/

	public BookingType getBookingTypeForTime(Employee employee, LocalDate date, LocalTime time) throws DataAccessException {
	    List<Booking> bookings = employeeBookings.get(employee);
	    if (bookings != null) {
	        for (Booking booking : bookings) {
	            if (booking.getBookingDate().toLocalDate().equals(date) && booking.getBookingDate().toLocalTime().equals(time)) {
	                return booking.getType();
	            }
	        }
	    }
	    return BookingType.available;
	}

	
/*
	public List<LocalTime> findAvailableTimes(Employee employee, LocalDate date) throws DataAccessException {
		
		if (bookingInSystem == null || bookingInSystem.getBookingDate() == null) {
	        throw new NullPointerException("bookingInSystem eller bookingDate er ikke initialiseret");
	    }

		List<LocalTime> availableTimesList = new ArrayList<>();
	    LocalTime startTime = LocalTime.of(9, 0); // Eksempel starttidspunkt
	    LocalTime endTime = LocalTime.of(18, 0); // Eksempel sluttidspunkt
	    
	    List<Booking> bookings = new ArrayList<>();
	    if(employeeBookings.containsKey(employee)) {
	    	for(Booking booking : employeeBookings.get(employee)) {
	    		if(booking.getBookingDate().toLocalDate().equals(date)) {
	    			bookings.add(booking);
	    		}
	    	}
	    }
	    	  


	  //  List<Booking> bookings = bookingDB.findBookingByDate(bookingInSystem.getBookingDate().toLocalDate());

	    // Logik til at finde ledige tidspunkter
	    for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
	        boolean isAvailable = true;
	        for (Booking booking : bookings) {
	            LocalTime bookingStart = booking.getBookingDate().toLocalTime();
	            LocalTime bookingEnd = bookingStart.plusMinutes(30); // Antag 30 minutters booking
	            if (!time.isBefore(bookingStart) && time.isBefore(bookingEnd)) {
	                isAvailable = false;
	                break;
	            }
	        }
	        if (isAvailable) {
	            availableTimesList.add(time);
	        }
	    }

	    return availableTimesList;
	}
	public BookingType getBookingTypeForTime(Employee employee, LocalDate date, LocalTime time) throws DataAccessException {
	    List<Booking> bookings = employeeBookings.get(employee);
	    if (bookings != null) {
	        for (Booking booking : bookings) {
	            if (booking.getBookingDate().toLocalDate().equals(date) && 
	                booking.getBookingDate().toLocalTime().equals(time)) {
	                return booking.getType();
	            }
	        }
	    }
	    return BookingType.available;
	}
*/

	//public void cancelBooking(int bookingId) {
//	}

/*
	public List<LocalTime> findAvailableTimes() throws DataAccessException {
		List<LocalTime> avaliableTimesList = null;
		//tjekker om datoen er søndag
		//if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
		//	System.out.println("Ikke muligt at book en søndag" + date);
		//} 
		
		List<Booking> boo = bookingDB.findBookingByDate(bookingInSystem.getBookingDate().toLocalDate());
		return avaliableTimesList;
	

	}
	*/
	/*
	public List<LocalTime> findAvailableTimes() throws DataAccessException {
	    if (bookingInSystem == null || bookingInSystem.getBookingDate() == null) {
	        throw new NullPointerException("bookingInSystem eller bookingDate er ikke initialiseret");
	    }

	    List<LocalTime> availableTimesList = new ArrayList<>();

	    if (bookingInSystem.getBookingDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
	        System.out.println("Ikke muligt at booke en søndag: " + bookingInSystem.getBookingDate());
	    }

	    List<Booking> bookings = bookingDB.findBookingByDate(bookingInSystem.getBookingDate().toLocalDate());

	    // Tilføj logik for at udfylde availableTimesList baseret på bookings
	    for (Booking booking : bookings) {
	        // Logik til at finde ledige tidspunkter og fylde dem i availableTimesList
	    }

	    return availableTimesList;
	}
	*/

	
}// map til hver employee og tilknytte en liste af booking.

