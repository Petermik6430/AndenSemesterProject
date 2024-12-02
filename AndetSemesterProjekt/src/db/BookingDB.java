package db;

import java.sql.Connection;
//BookingDB.java
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import model.Booking;

public class BookingDB implements BookingDBIF {
	private DBConnection dbc;
	private Connection con;
	
	private ServiceDBIF serviceDB;
	private CustomerDBIF customerDB;
	private EmployeeDBIF employeeDB;
	
	private static final String FIND_BY_BOOKING_ID_SQL = "FIND_BY_BOOKING_ID_SQL";
	private static final String FIND_BY_BOOKING_BY_DATE_SQL = "FIND_BY_BOOKING_BY_DATE_SQL";
	private static final String SAVE_BOOKING = "insert into Booking (id,date, type, note, employeeId, customerId) values(?,?,?,?,?,?)";
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
		try {
			int customerId = customerDB.createCustomer(booking.getCustomer());
			booking.getCustomer().setCustomerid(customerId);
		} catch(DataAccessException e) {
			dbc.rollbackTransaction();
			throw new DataAccessException("fejl", e);
		}
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
