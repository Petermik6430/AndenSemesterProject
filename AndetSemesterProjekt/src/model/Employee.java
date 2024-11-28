package model;

public class Employee {

	    private int employeeId;
	    private String fName;
	    private String lName;
	    private String phoneNo;
	    private String mail;

	    //constructor
	    public Employee(int employeeId, String fName, String lName, String phoneNo, String mail) {
	        this.employeeId = employeeId;
	        this.fName = fName;
	        this.lName = lName;
	        this.phoneNo = phoneNo;
	        this.mail = mail;
	    }

	    //get methods
	    public int getEmployeeId() {
	        return employeeId;
	    }

	    public String getFirstName() {
	        return fName;
	    }

	    public String getLastName() {
	        return lName;
	    }

	    public String getPhoneNo() {
	        return phoneNo;
	    }

	    public String getMail() {
	        return mail;
	    }

	  //set methods
	    public void setEmployeeId(int employeeId) {
	        this.employeeId = employeeId;
	    }

	    public void setFirstName(String fName) {
	        this.fName = fName;
	    }

	    public void setLastName(String lName) {
	        this.lName = lName;
	    }

	    public void setPhoneNo(String phoneNo) {
	        this.phoneNo = phoneNo;
	    }

	    public void setMail(String mail) {
	        this.mail = mail;
	    }
	}

	
	

