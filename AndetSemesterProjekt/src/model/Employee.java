package model;

public class Employee {

	    private int employeeId;
	    private String fName;
	    private String lName;
	    private String phoneNo;
	    private String email;
	    private String cprNo;
	    private double salary;
	    private String address;
		

	    //constructor
	    public Employee(int employeeId, String fName, String lName, String phoneNo, String email, 
	    				String cprNo, double salary, String address) {
	        this.employeeId = employeeId;
	        this.fName = fName;
	        this.lName = lName;
	        this.phoneNo = phoneNo;
	        this.email = email;
	        this.cprNo = cprNo;
	        this.salary = salary;
	        this.address = address;
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

	    public String getEmail() {
	        return email;
	    }
	    
	    public String getCprNo() {
	        return cprNo;
	    }
	    
	    public double getSalary() {
	        return salary;
	    }
	    
	    public String getAddress() {
	        return address;
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

	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
	    public void setCprNo(String cprNo) {
	        this.cprNo = cprNo;
	    }
	    
	    public void setSalary(double salary) {
	        this.salary = salary;
	    }
	    
	    public void setAddress(String address) {
	        this.address = address;
	    }

		
		
	}

	
	

