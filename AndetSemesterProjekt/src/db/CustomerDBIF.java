package db;

import model.Customer;

public interface CustomerDBIF {

	  // public void createCustomer(int customerId, String fName, String lName, String phoneNo, String mail);
	    
	    Customer findCustomerByPhoneNo(String phoneNo);

		public int createCustomer(Customer customer);

}

