package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Booking {
	private int bookingId;
	private Service service;
	private BookingType type = BookingType.booked;
	private LocalDateTime bookingDate;
	private String barber;
	private Customer customer;
	private Employee employee;
	private String note;


	public Booking() {
		
	}
	
	public Booking(Employee employee, LocalDateTime bookingDate, BookingType type) { 
		this.employee = employee; this.bookingDate = bookingDate; this.type = type; 
		}
	

	public int getBookingId() {
		return bookingId;
	}

	public Service getService() {
		return service;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public LocalDateTime getStartTime() {
		return bookingDate;
	}

	public LocalDateTime getEndTime() {
		return bookingDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	
	public String getNote() {
		return note; 
		}
	 
	
	public BookingType getType() {
		return type;
	}
	

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public void setService( Service service) {
		this.service = service;
	}

	public void setStartTime(LocalTime Bookingdate) {
		LocalDate start = this.bookingDate.toLocalDate();
		this.bookingDate = LocalDateTime.of(start, Bookingdate);
	}

	public void setEndTime(LocalTime endTime) { // TODO ved ikke om denne kode er 
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
	 public void setNote(String note) { 
		  this.note = note; 
		  }
	 
	
	public void setBookingType(BookingType type) {
		this.type = type;
	}

	@Override 
	public boolean equals(Object o) {
		if (this == o) return true; 
		if (o == null || getClass() != o.getClass()) return false; 
		Booking booking = (Booking) o;
		return bookingId == booking.bookingId && 
				Objects.equals(service, booking.service) && 
				Objects.equals(bookingDate, booking.bookingDate) && 
				Objects.equals(barber, booking.barber) && 
				Objects.equals(customer, booking.customer) && 
				Objects.equals(employee, booking.employee);
		} 
	
	@Override 
	public int hashCode() { 
		return Objects.hash(bookingId, service, bookingDate, barber, customer, employee);
		}


	@Override
	public String toString() {
		return "booking{" + "time=" + bookingDate + ", Status" + type + "}";
		
	}



	
	
}

/*package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Booking {
	private int bookingId;
	private Service service;
	private BookingType type;
	private LocalDateTime bookingDate;
	private String barber;
	private Customer customer;
	private Employee employee;
	private String note;

	public int getBookingId() {
		return bookingId;
	}

	public Service getService() {
		return service;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public LocalDateTime getStartTime() {
		return bookingDate;
	}

	public LocalDateTime getEndTime() {
		return bookingDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public String getNote() {
		return null;
	}
	
	public BookingType getType() {
		return type;
	}
	

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public void setService( Service service) {
		this.service = service;
	}

	public void setStartTime(LocalTime Bookingdate) {
		LocalDate start = this.bookingDate.toLocalDate();
		this.bookingDate = LocalDateTime.of(start, Bookingdate);
	}

	public void setEndTime(LocalTime endTime) { // TODO ved ikke om denne kode er 
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public void setBookingType(BookingType type) {
		this.type = type;
	}

	@Override 
	public boolean equals(Object o) {
		if (this == o) return true; 
		if (o == null || getClass() != o.getClass()) return false; 
		Booking booking = (Booking) o;
		return bookingId == booking.bookingId && 
				Objects.equals(service, booking.service) && 
				Objects.equals(bookingDate, booking.bookingDate) && 
				Objects.equals(barber, booking.barber) && 
				Objects.equals(customer, booking.customer) && 
				Objects.equals(employee, booking.employee);
		} 
	
	@Override 
	public int hashCode() { 
		return Objects.hash(bookingId, service, bookingDate, barber, customer, employee);
		}


	@Override
	public String toString() {
		return "booking{" + "time=" + bookingDate + ", Status" + type + "}";
		
	}



	
	
}
*/
