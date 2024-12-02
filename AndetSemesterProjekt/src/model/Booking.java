package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Booking {
	private int bookingId;
	private int serviceType;
	private LocalDateTime bookingDate;
	private String barber;
	private Customer customer;
	private Employee employee;

	public int getBookingId() {
		return bookingId;
	}

	public int getService() {
		return serviceType;
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

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public void setService(Service service) {
		this.serviceType = service.getServiceId();
	}

	public void setStartTime(LocalTime Bookingdate) {
		LocalDate start = this.bookingDate.toLocalDate();
		this.bookingDate = LocalDateTime.of(start, Bookingdate);
	}

	public void setEndTime(LocalTime endTime) { // TODO ved ikke om denne kode er relevant
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
	
}
