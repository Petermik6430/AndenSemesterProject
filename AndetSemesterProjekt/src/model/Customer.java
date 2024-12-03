package model;


public class Customer {
		private int customerId; 
		private String fName; 
		private String lName; 
		private String phoneNo; 
		private String email; 
		
	
		
		// constructor 
	
		public Customer() {
			
		}
		
		public Customer(int customerId, String fName, String lName, String phoneNo, String email) {
			this.customerId = customerId;
			this.fName = fName;
			this.lName = lName;
			this.phoneNo = phoneNo;
			this.email = email;
		}

		/* public Customer(int customerId, String fName, String lName, String phoneNo, String mail) {
		        this.customerId = customerId;
		        this.fName = fName;
		        this.lName = lName;
		        this.phoneNo = phoneNo;
		        this.mail = mail;
		 }
*/
		public int getCustomerid() {
			return customerId;
		}

		public void setCustomerid(int customerid) {
			this.customerId = customerid;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}  
	
}
