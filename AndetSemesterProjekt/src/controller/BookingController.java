package controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

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
	private Map <Employee, List<Booking>> employeeBookings;
	private Booking booking;
	
	private final LocalTime startOfDayWeekDays = LocalTime.of(9, 0);
	private final LocalTime endOfDayWeekDays = LocalTime.of(18, 0);
	private final LocalTime startOfDaySaturDay = LocalTime.of(10, 0);
	private final LocalTime endOfDaySaturDay = LocalTime.of(15, 0);

	public BookingController() throws DataAccessException {
		cc = new CustomerController();
		ec = new EmployeeController();
		sc = new ServiceController();
		bookingDB = new BookingDB();
		employeeBookings = new HashMap<>();

	}


public Booking createBooking() throws DataAccessException { 
	Booking res = new Booking(); 
	
	booking = res;
	
	return res;
}





	public void setService(Service service) {
		booking.setService(service);
		
	}

	public void setNote(String note) {
		booking.setNote(note);
	}
	
	public void setEmployee(Employee employee) {
		booking.setEmployee(employee);
		
		
	
	}

	public List<LocalTime> setDate(LocalDate date) {
		List<LocalTime> res = new ArrayList<>();
		booking.setBookingDate(LocalDateTime.of(date, LocalTime.MIN));
		
		return res;
	}


	
	public Customer selectCustomerByPhoneNo(String phoneNo) throws DataAccessException {
		booking.setCustomer((cc.findCustomerByPhoneNo(phoneNo)));
		
		return booking;

	}
	
	/*
	private void findAvailableBookingTime(List<LocalTime> timeSlots) {
		LocalTime time = startOfDayWeekDays;
		while(time.isBefore(endOfDayWeekDays.minus(30, ChronoUnit.MINUTES)));
		timeSlots.add(time);
		time = time.plus(30, ChronoUnit.MINUTES);
		
	}
	*/
	public void setStaringTime(LocalTime time) {
		LocalDate date = booking.getBookingDate().toLocalDate();
		
		booking.setBookingDate(LocalDateTime.of(date, time));
	}
	/*
	public Map<Employee,List<TimeSlot>> findAvailableTimes(LocalDate date) throws DataAccessException {
		
		
		
		return null;
		
	}
	*/
	
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
	
		 
		
		
		/*private void fillTableWithAvailableTimes(DefaultTableModel tableModel, List<Employee> employees, LocalDate date) throws DataAccessException {
		        if (date == null) {
		            throw new DataAccessException("Date cannot be null", null);
		        }
		        System.out.println("Updating table for date: " + date);
		        tableModel.setRowCount(0); // Ryd eksisterende rækker

		        LocalTime startTime = LocalTime.of(9, 0);
		        LocalTime endTime = LocalTime.of(18, 0);

		        List<LocalTime> timeSlots = new ArrayList<>();
		        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
		            timeSlots.add(time);
		        }

		        for (LocalTime time : timeSlots) {
		            Object[] rowData = new Object[employees.size() + 1];
		            rowData[0] = time;

		            for (int i = 0; i < employees.size(); i++) {
		                Employee employee = employees.get(i);
		                if (employee == null) {
		                    throw new DataAccessException("Employee cannot be null", null);
		                }

		                List<TimeSlot> employeeTimeSlots = bookingController.findAvailableTimes(employee, date);

		                BookingType status = BookingType.booked;
		                for (TimeSlot slot : employeeTimeSlots) {
		                    if (slot.getTime().equals(time)) {
		                        status = slot.getStatus();
		                    }
		                }

		                System.out.println("Time: " + time + ", Employee: " + employee.getFirstName() + ", Status: " + status);

		                rowData[i + 1] = status;
		            }

		            tableModel.addRow(rowData);
		        }

		        System.out.println("Table updated for date: " + date);
		        ((DefaultTableModel) table.getModel()).fireTableDataChanged();
		        table.repaint();
		    }	
		    */ 		
		

//	    return null;
//	}
	/*
	public List<LocalTime> findAvailableTimes() throws DataAccessException {
		
		List<LocalTime> res = null;
		
		if(booking.getBookingDate() != null) {
		
		List<Booking> bookingsForTheDate = bookingDB.findBookingByDate(booking.getBookingDate().toLocalDate());
		List<LocalTime> timeSlots = new ArrayList<>();
		findAvailableBookingTime(timeSlots);
		
		}
		
		return null;
		
	}
	*/
	
	/*
	 private void fillTableWithAvailableTimes(List<Employee> employees, LocalDate date) throws DataAccessException {
	        if (date == null) {
	            throw new DataAccessException("Date cannot be null", null);
	
	        List<LocalTime> timeSlots = new ArrayList<>();

	        for (LocalTime time : timeSlots) {

	            for (int i = 0; i < employees.size(); i++) {
	                Employee employee = employees.get(i);
	                if (employee == null) {
	                    throw new DataAccessException("Employee cannot be null", null);
	                }

	                List<TimeSlot> employeeTimeSlots = booking.findAvailableTimes(employee, date);

	                BookingType status = BookingType.booked;
	                for (TimeSlot slot : employeeTimeSlots) {
	                    if (slot.getTime().equals(time)) {
	                        status = slot.getStatus();
	                    }
	                }

	            }

	  
	        }

	 
	    }

	 }
	*/
/*
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
	*/

	
/*
	public List<Booking> findBookingsByDate(LocalDate date) throws DataAccessException {
	    return bookingDB.updateBookingCalender(date);
	}


*/


	



	
}// map til hver employee og tilknytte en liste af booking.

