package controller;

import db.CustomerDB;
import db.CustomerDBIF;
import db.DataAccessException;
import model.Customer;


public class CustomerController {

	private CustomerDBIF customerDB;

	public CustomerController() throws DataAccessException {
		customerDB = new CustomerDB();
	}



	public Customer findCustomerByPhoneNo(String phoneNo) throws DataAccessException {
		Customer cus = customerDB.findCustomerByPhoneNo(phoneNo);

		return cus;

	}
	
	public Customer findCustomerById(int id) throws DataAccessException {
		Customer cus = customerDB.findCustomerById(id);
		return cus;
		
	}
	
	
	
	
}