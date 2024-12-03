package controller;

import db.CustomerDB;
import db.CustomerDBIF;
import db.DataAccessException;
import model.Customer;

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
	


/*
	// fortæller hvad vi skal kunne gøre med databasen gemme kunde og finde en kunde baseret på telefonnumnmer
	public interface CustomerDBIF {
	    void saveCustomer(Customer customer);
	    Customer getCustomerByPhone(String phoneNo);
	}
	*/
	
	
/*
public class customerController { 
	private customerDBIF customerDB; 
	
	public customerController(customerDBIF customerDB) { 
		this.customerDB = customerDB; 
		}

	 
	
/*	
	 public void createCustomer(int customerId, String fName, String lName, String phoneNo, String mail) {
	        Customer newCustomer = new Customer(customerId, fName, lName, phoneNo, mail);
	        customerDB.saveCustomer(newCustomer); // Kalder databasegrænsefladen for at gemme kunden
	       
	 } 
	 
	// findCustomerByPhoneNo-metoden, der finder en kunde via telefonnummer
	    public Customer findCustomerByPhoneNo(String phoneNo) {
	        Customer customer = customerDB.getCustomerByPhone(phoneNo); // Kalder databasegrænsefladen for at hente kunden
	        if (customer != null) {
	            System.out.println("Kunde fundet: " + customer.getfName() + " " + customer.getlName());
	        } else {
	            System.out.println("Ingen kunde fundet med telefonnummeret: " + phoneNo);
	       
	        return customer;
	    }
	
	}
		
