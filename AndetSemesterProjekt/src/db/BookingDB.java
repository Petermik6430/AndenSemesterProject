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

import controller.EmployeeController;
import model.Booking;
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
	private static final String SAVE_BOOKING = "insert into Booking (date, type, note, employeeId, customerId, serviceId) values(?,?,?,?,?,?)";
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
	    // Method implementation
	    
	    dbc.startTransaction();
	    //try {
	        int customerId = customerDB.createCustomer(booking.getCustomer());
	        booking.getCustomer().setCustomerId(customerId);
	    //} //catch (DataAccessException e) {
	        //dbc.rollbackTransaction();
	        //throw new DataAccessException("fejl", e);
	    //}
	        // TODO try catch fejler vi skal spørger Gianna hvorfor den ikke kan bruge DataAccessExceptiom.

	    try {
	        ps_saveBooking.setObject(1, java.sql.Timestamp.valueOf(booking.getBookingDate()));
	     //   ps_saveBooking.setString(2, booking.getType().name());
	        ps_saveBooking.setString(3, booking.getNote());
	        ps_saveBooking.setInt(4, booking.getEmployee().getEmployeeId());
	        ps_saveBooking.setInt(5, booking.getCustomer().getCustomerId());
	        ps_saveBooking.setInt(6, booking.getService().getServiceId()); 
	        ps_saveBooking.executeUpdate(); // Make sure to execute the update

	        ResultSet generatedKeys = ps_saveBooking.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            booking.setBookingId(generatedKeys.getInt(1));
	        }
	    } catch (SQLException e) {
	        dbc.rollbackTransaction();
	        throw new DataAccessException("fejl", e);
	    }

	    dbc.commitTransaction();
	}
	
	public List<Booking> findBookingByDate(LocalDate date) throws DataAccessException {
		List<Booking> res = new ArrayList<>();
		LocalDate nextDay = date.plus(1, ChronoUnit.DAYS);
		try {
			ps_findBookingByDate.setDate(1, Date.valueOf(date));
			ps_findBookingByDate.setDate(2, Date.valueOf(nextDay));
			ResultSet rs = ps_findBookingByDate.executeQuery();
		res = buildObjects(rs); 
		} catch(SQLException e) {
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
