package model;


public class Customer {
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

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}  
	
}
