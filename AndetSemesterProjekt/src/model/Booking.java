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
	private Customer customer;
	private Employee employee;
	private String note;


	public Booking() {
		
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

}



	
	

