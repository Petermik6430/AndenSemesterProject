package controller;

public class CustomerController {

	// fortæller hvad vi skal kunne gøre med databasen gemme kunde og finde en kunde baseret på telefonnumnmer
	public interface CustomerDBIF {
	    void saveCustomer(Customer customer);
	    Customer getCustomerByPhone(String phoneNo);
	}
	
	
	
	public class customer { 
		private int customerId; 
		private String fName; 
		private String lName; 
		private String phoneNo; 
		private String mail; 
		
		
		
		
		// constructor 
		
		 public Customer(int customerId, String fName, String lName, String phoneNo, String mail) {
		        this.customerId = customerId;
		        this.fName = fName;
		        this.lName = lName;
		        this.phoneNo = phoneNo;
		        this.mail = mail;
		
	}
	
	// SPØRG JAWAD OG PETER ( setters and getters her? 

		public int getCustomerid() {
			return customerid;
		}

		public void setCustomerid(int customerid) {
			this.customerid = customerid;
		}

		public String getfName() {
			return fName;
		}

		public void setfName(String fName) {
			this.fName = fName;
		}

		public String getlName() {
			return lName;
		}

		public void setlName(String lName) {
			this.lName = lName;
		}

		public String getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		public string getMail() {
			return mail;
		}

		public void setMail(string mail) {
			this.mail = mail;
		}  
	
} 

public class customerController { 
	private customerDBIF customerDB; 
	
	public customerController(customerDBIF customerDB) { 
		this.customerDB = customerDB; 
		
	} 
	
	 public void createCustomer(int customerId, String fName, String lName, String phoneNo, String mail) {
	        Customer newCustomer = new Customer(customerId, fName, lName, phoneNo, mail);
	        customerDB.saveCustomer(newCustomer); // Kalder databasegrænsefladen for at gemme kunden
	        System.out.println("Kunde oprettet: " + fName + " " + lName);
	 } 
	 
	// findCustomerByPhoneNo-metoden, der finder en kunde via telefonnummer
	    public Customer findCustomerByPhoneNo(String phoneNo) {
	        Customer customer = customerDB.getCustomerByPhone(phoneNo); // Kalder databasegrænsefladen for at hente kunden
	        if (customer != null) {
	            System.out.println("Kunde fundet: " + customer.getfName() + " " + customer.getlName());
	        } else {
	            System.out.println("Ingen kunde fundet med telefonnummeret: " + phoneNo);
	        }
	        return customer;
	    }
	}
		
}

