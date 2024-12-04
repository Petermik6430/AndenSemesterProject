package controller;

import db.CustomerDB;
import db.DataAccessException;
import model.Customer;


public class CustomerController {

	private CustomerDB customerDB;

	public CustomerController() throws DataAccessException {
		customerDB = new CustomerDB();
	}

	public Customer createCustomer(int customerId, String fName, String lName, String phoneNo, String email) {
		Customer customer = new Customer(customerId, fName, lName, phoneNo, email);
		return customer; 
	}

	public Customer findCustomerByPhoneNo(String phoneNo) throws DataAccessException {
		Customer cus = customerDB.findCustomerByPhoneNo(phoneNo);

		return cus;

	}
}

//tror ikke dette skal være der.
	// fortæller hvad vi skal kunne gøre med databasen gemme kunde og finde en kunde baseret på telefonnumnmer
	/*public interface CustomerDBIF {
	    void saveCustomer(Customer customer);
	    
		Customer findCustomerByPhoneNo(String phoneNo);
	}
	*/