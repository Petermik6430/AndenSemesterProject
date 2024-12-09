package db;

import java.sql.Connection;
import java.sql.Date;
//BookingDB.java
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import db.DataAccessException;
import controller.EmployeeController;
import model.Booking;
import model.BookingType;
import model.Customer;
import model.Employee;
import model.Service;

public class BookingDB implements BookingDBIF {
	private DBConnection dbc;
	private Connection con;
	
	private ServiceDBIF serviceDB;
	private CustomerDBIF customerDB;
	private EmployeeDBIF employeeDB;
	
	private static final String FIND_BY_BOOKING_ID_SQL = "select * from Booking where id = ?";
	private static final String FIND_BOOKING_BY_DATE_SQL ="SELECT * FROM BookingDate WHERE bookingDate >= ? AND bookingDate < ?"; //"SELECT * FROM Booking WHERE bookingDate >= ? AND bookingDate < ?"; // "SELECT * FROM Booking WHERE bookingDate >= ? AND bookingDate < ?"; // "select * from Booking where bookingDate <=cast (? as datetime) and bookingdate < cast(? as datetime)";
	//private static final String SAVE_BOOKING = "insert into BookingDate (bookingDate, type, note,  employeeId, customerId, serviceId) values(?,?,?,?,?,?)";
	private static final String SAVE_BOOKING = "insert into BookingDate (bookingDate, type, note,  employeeId, customerId, serviceId) values(?,?,?,?,?,?)";

	//	private static final String SAVE_BOOKING = "insert into Booking (date, type, employeeId, customerId, serviceId) values(?,?,?,?,?,?)";

	private PreparedStatement ps_findByBookingId;
	private PreparedStatement ps_findBookingByDate;
	private PreparedStatement ps_saveBooking;
	
	public BookingDB() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException{
		try {
			serviceDB = new ServiceDB();
			customerDB = new CustomerDB();
			employeeDB = new EmployeeDB(); // Constructoren i EmployeeDB er ikke blevet defineret
			
			dbc = DBConnection.getInstance();
			con = dbc.getConnection();
			
			ps_findBookingByDate = con.prepareStatement(FIND_BOOKING_BY_DATE_SQL);
			ps_saveBooking = con.prepareStatement(SAVE_BOOKING, Statement.RETURN_GENERATED_KEYS);
			
		} catch (DataAccessException | SQLException e) {
			throw new DataAccessException("",e);
		}
	}
	
	@Override
	public void createBooking(Booking booking) throws DataAccessException {
	    dbc.startTransaction();

	    try {
	    	
	        // Kontroller, om kunden allerede findes
	        Customer customer = booking.getCustomer();
	        if (customer.getCustomerId() == 0) { // Antag at 0 betyder, at kunden ikke er i systemet
	            Customer existingCustomer = customerDB.findCustomerByPhoneNo(customer.getPhoneNo());
	            if (existingCustomer != null) {
	                customer.setCustomerId(existingCustomer.getCustomerId());
	            } else {
	                int customerId = customerDB.createCustomer(customer);
	                customer.setCustomerId(customerId);
	            }
	        }
	        
	        System.out.println("Booking date: " + booking.getBookingDate()); System.out.println("Type: " + (booking.getType() != null ? booking.getType().name() : "null")); System.out.println("Note: " + booking.getNote()); System.out.println("Employee ID: " + booking.getEmployee().getEmployeeId()); System.out.println("Customer ID: " + booking.getCustomer().getCustomerId()); System.out.println("Service ID: "+ + booking.getService().getServiceId());
	        if (booking.getBookingDate() == null || booking.getType() == null || booking.getEmployee() == null || booking.getCustomer() == null || booking.getService() == null) { throw new DataAccessException("En eller flere booking attributter er null", null);	 }     
	        
	        String note = booking.getNote() != null && !booking.getNote().isEmpty() ? booking.getNote() : "Standard note for booking.";
	        
	        ps_saveBooking.setObject(1, java.sql.Timestamp.valueOf(booking.getBookingDate()));
	        ps_saveBooking.setString(2, booking.getType() != null ? booking.getType().name() : BookingType.available.name());
	        ps_saveBooking.setString(3, note);
	        ps_saveBooking.setInt(4, booking.getEmployee().getEmployeeId());
	        ps_saveBooking.setInt(5, booking.getCustomer().getCustomerId());
	        ps_saveBooking.setInt(6, booking.getService().getServiceId()); 
	        ps_saveBooking.executeUpdate(); 

	        ResultSet generatedKeys = ps_saveBooking.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            booking.setBookingId(generatedKeys.getInt(1));
	        }

	        dbc.commitTransaction();
	    } catch (SQLException e) {
	        dbc.rollbackTransaction();
	        throw new DataAccessException("Fejl ved oprettelse af booking", e);
	    } catch (DataAccessException e) {
	        dbc.rollbackTransaction();
	        throw new DataAccessException("Fejl ved oprettelse af booking", e);
	    }
	}
	


	
	public List<Booking> findBookingByDate(LocalDate date) throws DataAccessException {
		List<Booking> res = new ArrayList<>();
		LocalDate nextDay = date.plus(1, ChronoUnit.DAYS);
		dbc.startTransaction();
		try {
			ps_findBookingByDate.setDate(1, Date.valueOf(date));
			ps_findBookingByDate.setDate(2, Date.valueOf(nextDay));
			ResultSet rs = ps_findBookingByDate.executeQuery();
		res = buildObjects(rs);
		dbc.commitTransaction();
		} catch(SQLException e) {
			dbc.rollbackTransaction();
			throw new DataAccessException("fejl ved at finde bookinger på dato" + date, e);
		}
		
		return res;
	}

	
	private List<Booking> buildObjects(ResultSet rs) throws DataAccessException {
	    List<Booking> res = new ArrayList<>();
	    try {
	        while (rs.next()) {
	            Booking booking = buildObject(rs);
	            res.add(booking);
	        }
	    } catch (SQLException e) {
	        throw new DataAccessException("Fejl ved opbygning af booking-objekter", e);
	    }
	    return res;
	    
	    

	}

	private Booking buildObject(ResultSet rs) throws DataAccessException {
	    Booking booking = new Booking();
	    try {
	        booking.setBookingId(rs.getInt("id"));
	        booking.setBookingDate(rs.getTimestamp("bookingDate").toLocalDateTime());
	        booking.setNote(rs.getString("note"));

	        // Hent og sæt Service objekt
	        int serviceId = rs.getInt("id");
	        if (!rs.wasNull()) {
	            Service service = serviceDB.findServiceById(serviceId);
	            booking.setService(service);
	        }

	        // Hent og sæt Employee objekt
	        int employeeId = rs.getInt("id");
	        if (!rs.wasNull()) {
	            Employee employee = employeeDB.findEmployeeById(employeeId);
	            booking.setEmployee(employee);
	        }
	        // Hent og sæt Customer objekt
	        int customerId = rs.getInt("id");
	        if (!rs.wasNull()) {
	            Customer customer = customerDB.findCustomerById(customerId);
	            booking.setCustomer(customer);
	        }
	        
	        String bookingType = rs.getString("type");
	        if (bookingType !=null) {
	        	booking.setBookingType(BookingType.valueOf(bookingType));
	        //	} else {
	        //		booking.setBookingType(BookingType.booked);
	        }

	    } catch (SQLException e) {
	        System.err.println("SQLException ved opbygning af booking-objekt: " + e.getMessage());
	        throw new DataAccessException("Fejl ved opbygning af booking-objekt", e);
	    } catch (NullPointerException e) {
	        System.err.println("NullPointerException ved opbygning af booking-objekt: " + e.getMessage());
	        throw new DataAccessException("Fejl ved opbygning af booking-objekt", e);
	    }
	    return booking;
	}
	

	

	@Override
	public Booking findBookingByCustomerPhoneNo(String phoneNo) {
		// Method implementation
		return null;
	}

	@Override
	public void updateBooking(Booking booking) {
		// Method implementation
	}

	@Override
	public void deleteBooking(int bookingId) {
		// Method implementation
	}




}
