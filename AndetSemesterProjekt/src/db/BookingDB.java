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
	

	private ServiceDBIF serviceDB;
	private CustomerDBIF customerDB;
	private EmployeeDBIF employeeDB;
	private DBConnection dbc;
	private Connection con;
	
	private static final String UPDATE_BOOKINGS = "select * from Booking where cast(bookingDate as date) = ?";
	private static final String SAVE_BOOKING = "insert into Booking (bookingDate, type, note,  employeeId, customerId, serviceId) values(?,?,?,?,?,?)";
	private static final String FIND_BOOKING_BY_DATE = "select * from BookingDate where bookingDate >= ? and bookingDate < ?";
	
	private PreparedStatement ps_findBookingByDate;
	private PreparedStatement ps_updateBookings;
	private PreparedStatement ps_saveBooking;
	
	public BookingDB() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException{
		try {
			serviceDB = new ServiceDB();
			customerDB = new CustomerDB();
			employeeDB = new EmployeeDB();
			
			dbc = DBConnection.getInstance();
			con = dbc.getConnection();
			
			ps_findBookingByDate = con.prepareStatement(FIND_BOOKING_BY_DATE);
			ps_updateBookings = con.prepareStatement(UPDATE_BOOKINGS);
			ps_saveBooking = con.prepareStatement(SAVE_BOOKING, Statement.RETURN_GENERATED_KEYS);
			
		} catch (DataAccessException | SQLException e) {
			throw new DataAccessException("",e);
		}
	}
	

	@Override
	public int createBooking(Booking booking) throws DataAccessException {
	    int bookingId = -1;

	    dbc.startTransaction();

	    try {
	        ps_saveBooking.setObject(1, java.sql.Timestamp.valueOf(booking.getBookingDate()));
	        ps_saveBooking.setString(2, booking.getType() != null ? booking.getType().name() : BookingType.available.name());
	        ps_saveBooking.setString(3, booking.getNote());
	        ps_saveBooking.setInt(4, booking.getEmployee().getEmployeeId());
	        ps_saveBooking.setInt(5, booking.getCustomer().getCustomerId());
	        ps_saveBooking.setInt(6, booking.getService().getServiceId());
	        ps_saveBooking.executeUpdate();

	        ResultSet generatedKeys = ps_saveBooking.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            bookingId = (generatedKeys.getInt(1));
	        }

	        dbc.commitTransaction();
	    } catch (SQLException e) {
	        dbc.rollbackTransaction();
	        throw new DataAccessException("Fejl ved oprettelse af booking", e);
	    } 
	    
	    return bookingId;
	}

	
	
	public List<Booking> updateBookingCalender(LocalDate bookingDate) throws DataAccessException {
	    List<Booking> bookings = new ArrayList<>();
	    try {
	        ps_updateBookings.setDate(1, java.sql.Date.valueOf(bookingDate));
	        ResultSet rs = ps_updateBookings.executeQuery();
	        while (rs.next()) {
	            bookings.add(buildObject(rs));
	        }
	    } catch(SQLException e) {
	        dbc.rollbackTransaction();
	        throw new DataAccessException("Fejl ved opdatering af bookingkalender", e);
	    }
	    return bookings;
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
	        int serviceId = rs.getInt("serviceId");
	            Service service = serviceDB.findServiceById(serviceId);
	            booking.setService(service);
	        int employeeId = rs.getInt("employeeId");
	            Employee employee = employeeDB.findEmployeeById(employeeId);
	            booking.setEmployee(employee);
	        int customerId = rs.getInt("customerId");
	            Customer customer = customerDB.findCustomerById(customerId);
	            booking.setCustomer(customer);
	        String bookingType = rs.getString("type");
	        	booking.setBookingType(BookingType.valueOf(bookingType));


	    } catch (SQLException e) {
	        throw new DataAccessException("Fejl ved opbygning af booking-objekt", e);
	    } 
	    return booking;
	}
	
	


}
