package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Booking {
	private int bookingId;
	private Service service;
	private BookingType type;
	private LocalDateTime bookingDate;
	private String barber;
	private Customer customer;
	private Employee employee;

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

	public void setService( int serviceId) {
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
	}


	public void setBookingType(BookingType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "booking{" + "time=" + bookingDate + ", Status" + type + "}";
		
	}
	
}
