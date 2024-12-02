package db;

import model.Customer;

public interface CustomerDBIF {

	  // public void createCustomer(int customerId, String fName, String lName, String phoneNo, String mail);
	    
	    public Customer findCustomerByPhoneNo(String phoneNo) throws DataAccessException;

		public int createCustomer(Customer customer) throws DataAccessException;

}

