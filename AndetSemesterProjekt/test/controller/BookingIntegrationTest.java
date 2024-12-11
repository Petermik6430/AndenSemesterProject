/*
package controller;

import db.DataAccessException;
import model.Booking;
import model.Customer;
import model.Employee;
import model.Service;
import model.TimeSlot;
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
            Service service = services.get(0);
            Customer customer = bookingController.selectCustomerByPhoneNo("12345678");
            List<Employee> employees = bookingController.getAllEmployees();
            assertFalse(employees.isEmpty());
            Employee employee = employees.get(0);

            // Act
            bookingController.setService(service);
            bookingController.setDate(LocalDate.now());
            bookingController.setStaringTime(LocalTime.now());
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
            bookingController.setDate(LocalDate.now());
            bookingController.setStaringTime(LocalTime.now());
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
            bookingController.setDate(LocalDate.now());
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
            LocalDate selectedDate = LocalDate.now();
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
}

*/
package controller;

import controller.BookingController;
import db.DataAccessException;
import model.Booking;
import model.Customer;
import model.Employee;
import model.Service;
import model.TimeSlot;
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
        Booking booking = bookingController.createBooking();
        assertNotNull(booking, "Booking should not be null");
    }

    @Test
    public void testSetService() {
        try {
            List<Service> services = bookingController.getAllServices();
            Service service = services.get(0);
            Customer customer = bookingController.selectCustomerByPhoneNo("12345678");
            List<Employee> employees = bookingController.getAllEmployees();
            Employee employee = employees.get(0);
            assertFalse(services.isEmpty(), "Services list should not be empty");
          
            bookingController.createBooking();
            bookingController.setService(service);
            bookingController.setDate(LocalDate.now());
            bookingController.setStaringTime(LocalTime.now());
            bookingController.setEmployee(employee);
            // Ensure customer, date, time, and employee are set before completing booking
           
           


            
         
            assertFalse(services.isEmpty());
            assertNotNull(customer);
            assertFalse(employees.isEmpty());
            assertEquals(service, bookingController.completeBooking().getService());
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

    @Test
    public void testSetNote() {
        String note = "Test note";
        bookingController.createBooking();
        bookingController.setNote(note);

        // Ensure customer, service, date, time, and employee are set before completing booking
        try {
            Customer customer = bookingController.selectCustomerByPhoneNo("12345678");
            List<Service> services = bookingController.getAllServices();
            Service service = services.get(0);
            List<Employee> employees = bookingController.getAllEmployees();
            Employee employee = employees.get(0);


         
            bookingController.setService(service);
            bookingController.setDate(LocalDate.now());
            bookingController.setStaringTime(LocalTime.now());
            bookingController.setEmployee(employee);
            
            
            assertNotNull(customer);
            assertFalse(services.isEmpty());
            assertFalse(employees.isEmpty());
            assertEquals(note, bookingController.completeBooking().getNote());
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

    @Test
    public void testSetEmployee() {
        try {
            List<Employee> employees = bookingController.getAllEmployees();
            Employee employee = employees.get(0);
            Customer customer = bookingController.selectCustomerByPhoneNo("12345678");
            List<Service> services = bookingController.getAllServices();
            Service service = services.get(0);
            
            
            
            bookingController.createBooking();
            bookingController.setEmployee(employee);
            bookingController.setService(service);
            bookingController.setDate(LocalDate.now());
            bookingController.setStaringTime(LocalTime.now());
            // Ensure customer, service, date, and time are set before completing booking
           
            assertFalse(employees.isEmpty());
            assertNotNull(customer);
            assertFalse(services.isEmpty());
            assertEquals(employee, bookingController.completeBooking().getEmployee());
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

    @Test
    public void testSelectCustomerByPhoneNo() {
        try {
            String phoneNo = "12345678";
            Customer customer = bookingController.selectCustomerByPhoneNo(phoneNo.trim());
            List<Service> services = bookingController.getAllServices();
            Service service = services.get(0);
            List<Employee> employees = bookingController.getAllEmployees();
            Employee employee = employees.get(0);
            
            
            bookingController.setEmployee(employee);
            bookingController.setService(service);
            bookingController.createBooking();
            bookingController.setDate(LocalDate.now());
            bookingController.setStaringTime(LocalTime.now());

            
            assertNotNull(customer);
            assertEquals(phoneNo, customer.getPhoneNo().trim());
            assertFalse(services.isEmpty());
            assertFalse(employees.isEmpty());
            assertNotNull(bookingController.completeBooking().getCustomer());
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

    @Test
    public void testSetDate() {
        LocalDate date = LocalDate.now();
        bookingController.createBooking();
        bookingController.setDate(date);

        // Ensure customer, service, time, and employee are set before completing booking
        try {
            Customer customer = bookingController.selectCustomerByPhoneNo("12345678");
            List<Service> services = bookingController.getAllServices();
            Service service = services.get(0);
            List<Employee> employees = bookingController.getAllEmployees();
            Employee employee = employees.get(0);
            
    
            bookingController.setService(service);

            bookingController.setStaringTime(LocalTime.now());
            bookingController.setEmployee(employee);
            
            assertNotNull(customer);
            assertFalse(services.isEmpty());
            assertFalse(employees.isEmpty());
            assertEquals(date, bookingController.completeBooking().getBookingDate().toLocalDate());
            
            
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

    @Test
    public void testSetStaringTime() {
        LocalTime time = LocalTime.of(10, 0);
        bookingController.createBooking();
        bookingController.setStaringTime(time);

        // Ensure customer, service, date, and employee are set before completing booking
        try {
            Customer customer = bookingController.selectCustomerByPhoneNo("12345678");
            List<Service> services = bookingController.getAllServices();
            Service service = services.get(0);
            List<Employee> employees = bookingController.getAllEmployees();
            Employee employee = employees.get(0);

            bookingController.setService(service);
            bookingController.setDate(LocalDate.now());
            bookingController.setEmployee(employee);
            
            
            assertNotNull(customer);
            assertFalse(services.isEmpty());
            assertFalse(employees.isEmpty());
            assertEquals(time, bookingController.completeBooking().getBookingDate().toLocalTime());
            
            
            
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }

    @Test
    public void testCompleteBooking() {
        try {
            String phoneNo = "12345678";
            LocalDate selectedDate = LocalDate.now();
            LocalTime startTime = LocalTime.of(10, 0);
            List<Employee> employees = bookingController.getAllEmployees();
            List<Service> services = bookingController.getAllServices();

            if (employees.isEmpty() || services.isEmpty()) {
                fail("Employees or Services list is empty");
            }

            Employee employee = employees.get(0);
            Service service = services.get(0);

            bookingController.createBooking();
            bookingController.selectCustomerByPhoneNo(phoneNo);
            bookingController.setService(service);
            bookingController.setEmployee(employee);
            bookingController.setStaringTime(startTime);
            bookingController.setDate(selectedDate);
            Booking booking = bookingController.completeBooking();

            assertNotNull(booking, "Booking should not be null");
            assertNotNull(booking.getBookingId(), "Booking ID should not be null");
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }
}
/*
    @Test
    public void testFindAvailableTimes() {
        try {
            List<Employee> employees = bookingController.getAllEmployees();
            assertFalse(employees.isEmpty(), "Employees list should not be empty");
            Employee employee = employees.get(0);

            LocalDate date = LocalDate.now();
            List<TimeSlot> timeSlots = bookingController.findAvailableTimes(employee, date);
            assertNotNull(timeSlots, "Time slots should not be null");
            assertFalse(timeSlots.isEmpty(), "Time slots should not be empty");
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }


    @Test
    public void testGetAvailableTimesTableData() {
        try {
            List<Employee> employees = bookingController.getAllEmployees();
            assertFalse(employees.isEmpty(), "Employees list should not be empty");
            LocalDate date = LocalDate.now();
            List<Object[]> tableData = bookingController.getAvailableTimesTableData(employees, date);
            assertNotNull(tableData, "Table data should not be null");
            assertFalse(tableData.isEmpty(), "Table data should not be empty");
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }


    @Test
    public void testUpdateTableData() {
        try {
            LocalDate date = LocalDate.now();
            List<Object[]> tableData = bookingController.updateTableData(date);
            assertNotNull(tableData, "Table data should not be null");
            assertFalse(tableData.isEmpty(), "Table data should not be empty");
        } catch (DataAccessException e) {
            fail("DataAccessException: " + e.getMessage());
        }
    }
    */
//}


/*
package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DataAccessException;
import model.Booking;
import model.BookingType;
import model.Service;

class BookingIntegrationTest {

	private static BookingController bookingController;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		try {
	bookingController = new BookingController();
		} catch (DataAccessException e) {
			fail("Der skete fejl her");
		}
		} 



	@Test
	void testCreateBooking() {
		
	Booking created;
	Booking arranged = new Booking();
	
	created = bookingController.createBooking();
		assertEquals(created, arranged);
	}
	
	
	@Test
	void testAddDate() {
		bookingController.createBooking();
		LocalDate testDate = LocalDate.of(2018, 6, 15);
		List<LocalTime> expected = new ArrayList<>();
		int duration = 15;
		LocalTime startTime = LocalTime.of(16, 0);
		 while(startTime.isBefore(LocalTime.of(18, 00))) {
			 expected.add(startTime);
			 startTime = startTime.plusMinutes(duration);
		 }
		 
		 List<LocalTime> timeReturned = bookingController.setDateTime(testDate);
		 
		 for(LocalTime localTime : timeReturned) {
			 assertEquals(localTime, expected.get(timeReturned.indexOf(localTime)));
		 }
	}
	
	@Test
	void testAddServiceToBooking() {
	      	Booking booking = new Booking();
	        Service service = new Service();
	        service.setServiceId(5);
	        service.setName("Hårklipning");
	        service.setDuration(20);

	        booking.setService(service);

	        // Bekræft at serviceId er korrekt sat i booking
	        assertEquals(service, booking.getService());

	 
	        // Tilføj servicen til booking objektet
	        assertEquals(5, booking.getService().getServiceId());

	        // Bekræft at service objektet er korrekt sat i booking
	        assertNotNull(booking.getService());
	        assertEquals(service, booking.getService());
	        assertEquals("Hårklipning", booking.getService().getName());
	        assertEquals(20, booking.getService().getDuration());
	}
	




}

*/
