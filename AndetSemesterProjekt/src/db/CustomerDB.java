package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;

public class CustomerDB implements CustomerDBIF {

	private DBConnection dbc;
	private Connection con;
	
	private static final String FIND_CUSTOMER_BY_PHONENO = "select * from Customer where phoneNo = ?";
	private static final String FIND_CUSTOMER_BY_ID = "select fName, lName, phoneNo, email from Customer where id = ?";
	private static final String SAVE_CUSTOMER = "insert into Customer ( fName, lName, phoneNo, email) values(?,?,?,?)";
	
	private PreparedStatement ps_findCustomerByPhoneNo;
	private PreparedStatement ps_findCustomerById;
	private PreparedStatement ps_saveCustomer;
	
	public CustomerDB() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException {
		dbc = DBConnection.getInstance();
		con = DBConnection.getInstance().getConnection();
		try {
		ps_findCustomerById = con.prepareStatement(FIND_CUSTOMER_BY_ID);
		ps_findCustomerByPhoneNo = con.prepareStatement(FIND_CUSTOMER_BY_PHONENO);
		ps_saveCustomer = con.prepareStatement(SAVE_CUSTOMER);
		} catch (SQLException e) {
			throw new DataAccessException("", e); //TODO skriv en beskrivende fejlbesked
		}
	}

	
	
	@Override
	public Customer findCustomerByPhoneNo(String phoneNo) throws DataAccessException{
		Customer res = null;
		try {
			ps_findCustomerByPhoneNo.setString(1, phoneNo);
			ResultSet rs = ps_findCustomerByPhoneNo.executeQuery();
			if(rs.next()) {
				
			res = buildObject(rs);
			} else {
				System.err.println("No customer found with phone number: " + phoneNo);
			}
		} catch (SQLException e) {
			dbc.rollbackTransaction();
			throw new DataAccessException("fejl", e); //TODO skriv en beskrivende fejlbesked
		}
		return res;
	}
	
	

	private Customer buildObject(ResultSet rs) throws DataAccessException {
		Customer customer = new Customer();
		try {
			customer.setCustomerId(rs.getInt(1));
			customer.setFirstName(rs.getString(2));
			customer.setLastName(rs.getString(3));
			customer.setPhoneNo(rs.getString(4));
			customer.setEmail(rs.getString(5));
		}catch(SQLException e) {
			throw new DataAccessException("fejl",e); //TODO skriv en beskrivende fejlbesked
		}
		return customer;
	}
	
	
	
	public Customer findCustomerById(int id) throws DataAccessException {
	    Customer cus = null;
	    try {
	        ps_findCustomerById.setInt(1, id);
	        ResultSet rs = ps_findCustomerById.executeQuery();
	       if( rs.next()) {;
	        cus = buildObjectId(rs);
	       }
	    } catch (SQLException e) {
	        throw new DataAccessException("Error finding customer by id", e);
	    }
	    return cus;
	}
	
	

	private Customer buildObjectId(ResultSet rs) throws DataAccessException {
	    Customer customer = new Customer();
	    try {
	        customer.setFirstName(rs.getString(1));
	        customer.setLastName(rs.getString(2));
	        customer.setPhoneNo(rs.getString(3));
	        customer.setEmail(rs.getString(4));
	    } catch (SQLException e) {
	        throw new DataAccessException("Error building customer object", e);
	    }
	    return customer;
	}

	
	
	@Override
	public int createCustomer(Customer customer) throws DataAccessException {
		int customerId = -1;
		try {
			
			//ps_saveCustomer.setInt(1, customer.getCustomerId());
			ps_saveCustomer.setString(1, customer.getFirstName());
			ps_saveCustomer.setString(2, customer.getLastName());
			ps_saveCustomer.setString(3, customer.getPhoneNo());
			ps_saveCustomer.setString(4, customer.getEmail());
			ps_saveCustomer.executeUpdate();
			
			ResultSet generatedKeys = ps_saveCustomer.getGeneratedKeys();
			if(generatedKeys.next()) customerId = generatedKeys.getInt(1);
			
		  } catch (SQLException e) {
			throw new DataAccessException("", e); //TODO skriv en beskrivende fejlbesked
		}
		return customerId;

		
	}


	

	
	
	
}

