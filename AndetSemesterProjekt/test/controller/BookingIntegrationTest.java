package controller;

import db.DataAccessException;
import model.Booking;
import model.Customer;
import model.Employee;
import model.Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingIntegrationTest {
    private static BookingController bookingController;

    @BeforeAll
    public static void setup() {
        try {
            bookingController = new BookingController();
        } catch (DataAccessException e) {
            fail("Failed to initialize BookingController: " + e.getMessage());
        }
    }

	@Test
    public void testCreateBooking() {
        // Arrange
        Booking booking;

        // Act
        booking = bookingController.createBooking();

        // Assert
        assertNotNull(booking);
    }

    @Test
    public void testSetService() {
        try {
            // Arrange
            bookingController.createBooking();
            List<Service> services = bookingController.getAllServices();
            assertFalse(services.isEmpty());
            Service service = services.get(1);
            Customer customer = bookingController.selectCustomerByPhoneNo("12345678");
            List<Employee> employees = bookingController.getAllEmployees();
            assertFalse(employees.isEmpty());
            Employee employee = employees.get(0);

            // Act
            bookingController.setService(service);
            bookingController.setDate(LocalDate.of( 2010, 10, 10));
            bookingController.setStaringTime(LocalTime.of(10,30));
            bookingController.setEmployee(employee);
            
            Booking completedBooking = bookingController.completeBooking();

            // Assert
            assertNotNull(customer);
            assertEquals(service, completedBooking.getService());
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

    @Test
    public void testSetNote() {
        try {
            // Arrange
            bookingController.createBooking();
            String note = "Test note";
            List<Service> services = bookingController.getAllServices();
            assertFalse(services.isEmpty(), "Services list should not be empty");
            Service service = services.get(0);
            Customer customer = bookingController.selectCustomerByPhoneNo("12345678");
            List<Employee> employees = bookingController.getAllEmployees();
            assertFalse(employees.isEmpty(), "Employees list should not be empty");
            Employee employee = employees.get(0);

            // Act
            bookingController.setNote(note);
            bookingController.setService(service);
            bookingController.setDate(LocalDate.of(2010, 10, 10));
            bookingController.setStaringTime(LocalTime.of(10, 0));
            bookingController.setEmployee(employee);
            
            Booking completedBooking = bookingController.completeBooking();

            // Assert
            assertNotNull(customer);
            assertEquals(note, completedBooking.getNote(), "Note should match");
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

    @Test
    public void testSetEmployee() {
        try {
            // Arrange
            bookingController.createBooking();
            List<Employee> employees = bookingController.getAllEmployees();
            assertFalse(employees.isEmpty());
            Employee employee = employees.get(0);
            List<Service> services = bookingController.getAllServices();
            assertFalse(services.isEmpty());
            Service service = services.get(0);
            Customer customer = bookingController.selectCustomerByPhoneNo("12345678");

            // Act
            bookingController.setEmployee(employee);
            bookingController.setService(service);
            bookingController.setDate(LocalDate.now());
            bookingController.setStaringTime(LocalTime.now());
            
            Booking completedBooking = bookingController.completeBooking();

            // Assert
            assertNotNull(customer);
            assertEquals(employee, completedBooking.getEmployee());
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

    @Test
    public void testSelectCustomerByPhoneNo() {
        try {
            // Arrange
            bookingController.createBooking();
            String phoneNo = "12345678";
            Customer customer = bookingController.selectCustomerByPhoneNo(phoneNo.trim());
            List<Service> services = bookingController.getAllServices();
            assertFalse(services.isEmpty());
            Service service = services.get(0);
            List<Employee> employees = bookingController.getAllEmployees();
            assertFalse(employees.isEmpty());
            Employee employee = employees.get(0);

            // Act
            bookingController.setService(service);
            bookingController.setDate(LocalDate.of(2010, 10, 10));
            bookingController.setStaringTime(LocalTime.now());
            bookingController.setEmployee(employee);
            
            Booking completedBooking = bookingController.completeBooking();

            // Assert
            assertNotNull(customer);
            assertEquals(phoneNo, customer.getPhoneNo().trim());
            assertNotNull(completedBooking.getCustomer());
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

    @Test
    public void testSetDate() {
        try {
            // Arrange
            bookingController.createBooking();
            LocalDate date = LocalDate.now();
            List<Service> services = bookingController.getAllServices();
            assertFalse(services.isEmpty());
            Service service = services.get(0);
            Customer customer = bookingController.selectCustomerByPhoneNo("12345678");
            List<Employee> employees = bookingController.getAllEmployees();
            assertFalse(employees.isEmpty());
            Employee employee = employees.get(0);

            // Act
            bookingController.setDate(date);
            bookingController.setService(service);
            bookingController.setStaringTime(LocalTime.now());
            bookingController.setEmployee(employee);
            
            Booking completedBooking = bookingController.completeBooking();

            // Assert
            assertNotNull(customer);
            assertEquals(date, completedBooking.getBookingDate().toLocalDate(), "Date should match");
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

    @Test
    public void testSetStaringTime() {
        try {
            // Arrange
            bookingController.createBooking();
            LocalTime time = LocalTime.of(10, 0);
            List<Service> services = bookingController.getAllServices();
            assertFalse(services.isEmpty());
            Service service = services.get(0);
            Customer customer = bookingController.selectCustomerByPhoneNo("12345678");
            List<Employee> employees = bookingController.getAllEmployees();
            assertFalse(employees.isEmpty());
            Employee employee = employees.get(0);

            // Act
            bookingController.setStaringTime(time);
            bookingController.setService(service);
            bookingController.setDate(LocalDate.now());
            bookingController.setEmployee(employee);
            
            Booking completedBooking = bookingController.completeBooking();

            // Assert
            assertNotNull(customer);
            assertEquals(time, completedBooking.getBookingDate().toLocalTime(), "Time should match");
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }
    
    @Test
    public void testCompleteBooking() {
        try {
            // Arrange
            bookingController.createBooking();
            String phoneNo = "12345678";
            LocalDate selectedDate = LocalDate.of(2010, 10, 10);
            LocalTime startTime = LocalTime.of(10, 0);
            List<Employee> employees = bookingController.getAllEmployees();
            assertFalse(employees.isEmpty());
            Employee employee = employees.get(0);
            List<Service> services = bookingController.getAllServices();
            assertFalse(services.isEmpty());
            Service service = services.get(0);

            // Act
            Customer customer = bookingController.selectCustomerByPhoneNo(phoneNo);
            bookingController.setService(service);
            bookingController.setEmployee(employee);
            bookingController.setStaringTime(startTime);
            bookingController.setDate(selectedDate);
            Booking booking = bookingController.completeBooking();

            // Assert
            assertNotNull(booking);
            assertNotNull(booking.getBookingId());
            assertEquals(phoneNo, booking.getCustomer().getPhoneNo().trim());
            assertEquals(service.getServiceId(), booking.getService().getServiceId());
            assertEquals(employee.getEmployeeId(), booking.getEmployee().getEmployeeId());
            assertEquals(selectedDate, booking.getBookingDate().toLocalDate());
            assertEquals(startTime, booking.getBookingDate().toLocalTime());
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

/*
    @Test
    public void testCompleteBooking() {
        try {
            // Arrange
            bookingController.createBooking();
            String phoneNo = "12345678";
            LocalDate selectedDate = LocalDate.of(2010, 10, 10);
            LocalTime startTime = LocalTime.of(10, 0);
            List<Employee> employees = bookingController.getAllEmployees();
            assertFalse(employees.isEmpty());
            Employee employee = employees.get(0);
            List<Service> services = bookingController.getAllServices();
            assertFalse(services.isEmpty());
            Service service = services.get(0);

            // Act
            bookingController.selectCustomerByPhoneNo(phoneNo);
            bookingController.setService(service);
            bookingController.setEmployee(employee);
            bookingController.setStaringTime(startTime);
            bookingController.setDate(selectedDate);
            Booking booking = bookingController.completeBooking();

            // Assert
            assertNotNull(booking);
            assertNotNull(booking.getBookingId());
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }
    
    
  */  
}

