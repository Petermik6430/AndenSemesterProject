package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;

public class CustomerDB implements CustomerDBIF {

	private DBConnection dbc;
	private Connection con;
	
	private static final String FIND_CUSTOMER_BY_PHONENO = "select Customer where phoneNo = ?";
	private static final String SAVE_CUSTOMER = "insert into Customer (id, fName, lName, phoneNo, email) values(?,?,?,?,?)";
	
	private PreparedStatement ps_findCustomerByPhoneNo;
	private PreparedStatement ps_saveCustomer;
	
	public CustomerDB() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException {
		dbc = DBConnection.getInstance();
		con = DBConnection.getInstance().getConnection();
		try {
		ps_findCustomerByPhoneNo = con.prepareStatement(FIND_CUSTOMER_BY_PHONENO);
		ps_saveCustomer = con.prepareStatement(SAVE_CUSTOMER);
		} catch (SQLException e) {
			throw new DataAccessException("", e); //TODO skriv en beskrivende fejlbesked
		}
	}

	@Override
	public Customer findCustomerByPhoneNo(String phoneNo) throws DataAccessException{
		Customer cus = null;
		dbc.startTransaction();
		
		try {
			ps_findCustomerByPhoneNo.setString(1, phoneNo);
			ResultSet rs = ps_findCustomerByPhoneNo.executeQuery();
			rs.next();
			cus = buildObject(rs);
		} catch (SQLException e) {
			dbc.rollbackTransaction();
			throw new DataAccessException("fejl", e); //TODO skriv en beskrivende fejlbesked
		}
		dbc.commitTransaction();
		return cus;
	}

	private Customer buildObject(ResultSet rs) throws DataAccessException {
		Customer customer = new Customer();
		try {
			customer.setCustomerid(rs.getInt(1));
			customer.setfName(rs.getString(2));
			customer.setlName(rs.getString(3));
			customer.setPhoneNo(rs.getString(4));
			customer.setMail(rs.getString(5));
		}catch(SQLException e) {
			throw new DataAccessException("fejl",e); //TODO skriv en beskrivende fejlbesked
		}
		
		return customer;
	}

	@Override
	public int createCustomer(Customer customer) throws DataAccessException {
		int customerId = -1;
		try {
			ps_saveCustomer.setString(2, customer.getfName());
			ps_saveCustomer.setString(3, customer.getlName());
			ps_saveCustomer.setString(4,customer.getPhoneNo());
			ps_saveCustomer.setString(5, customer.getMail());
			ps_saveCustomer.executeUpdate();
			
			ResultSet generatedKeys = ps_saveCustomer.getGeneratedKeys();
			if(generatedKeys.next()) customerId = generatedKeys.getInt(1);
			
		  } catch (SQLException e) {
			throw new DataAccessException("", e); //TODO skriv en beskrivende fejlbesked
		}
		return customerId;

		
	} 
	

	
	
	
}
