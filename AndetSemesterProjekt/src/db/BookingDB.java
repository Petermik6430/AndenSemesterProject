package db;

import java.sql.Connection;
//BookingDB.java
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import db.DataAccessException;

import model.Booking;

public class BookingDB implements BookingDBIF {
	private DBConnection dbc;
	private Connection con;
	
	private ServiceDBIF serviceDB;
	private CustomerDBIF customerDB;
	private EmployeeDBIF employeeDB;
	
	private static final String FIND_BY_BOOKING_ID_SQL = "FIND_BY_BOOKING_ID_SQL";
	private static final String FIND_BY_BOOKING_BY_DATE_SQL = "FIND_BY_BOOKING_BY_DATE_SQL";
	private static final String SAVE_BOOKING = "insert into Booking (date, type, note, employeeId, customerId, serviceId) values(?,?,?,?,?,?)";
	private PreparedStatement ps_findByBookingId;
	private PreparedStatement ps_findByBookingDate;
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
	        booking.getCustomer().setCustomerid(customerId);
	    //} //catch (DataAccessException e) {
	        //dbc.rollbackTransaction();
	        //throw new DataAccessException("fejl", e);
	    //}
	        // TODO try catch fejler vi skal spørger Gianna hvorfor den ikke kan bruge DataAccessExceptiom.

	    try {
	        ps_saveBooking.setObject(1, java.sql.Timestamp.valueOf(booking.getBookingDate()));
	        ps_saveBooking.setString(2, booking.getType().name());
	        ps_saveBooking.setString(3, booking.getNote());
	        ps_saveBooking.setInt(4, booking.getEmployee().getEmployeeId());
	        ps_saveBooking.setInt(5, booking.getCustomer().getCustomerid());
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
