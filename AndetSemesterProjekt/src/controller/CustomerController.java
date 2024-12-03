package controller;

import db.CustomerDB;
import db.CustomerDBIF;
import db.DataAccessException;
import model.Customer;
import model.Employee;

public class CustomerController {
	
	private CustomerDBIF customerDB;
	private Customer customer;
	
	
	public CustomerController() throws DataAccessException {
		customerDB = new CustomerDB();
	}
	
	
	public Customer createCustomer (int customerId, String fName, String lName, String phoneNo, String email) {
		Customer customer = new Customer(customerId, fName, lName, phoneNo, email);
		return customer;
		
	}
	

	// fortæller hvad vi skal kunne gøre med databasen gemme kunde og finde en kunde baseret på telefonnumnmer
	public interface CustomerDBIF {
	    void saveCustomer(Customer customer);
	    
		Customer findCustomerByPhoneNo(String phoneNo);
	}
	
	public Customer findCustomerByPhoneNo(String phoneNo) throws DataAccessException {
		Customer cus = customerDB.findCustomerByPhoneNo(phoneNo); 
		
		return cus; 
	
	
	
	}
} 
