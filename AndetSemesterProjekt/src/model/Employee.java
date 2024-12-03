package model;

//Subclass Employee
public class Employee extends Person {
	private int employeeId;
	private String cprNo;
	private double salary;
	private String address;

	public Employee(int employeeId, String fName, String lName, String phoneNo, String email, String cprNo,
			double salary, String address) {
		super(fName, lName, phoneNo, email);
		this.employeeId = employeeId;
		this.cprNo = cprNo;
		this.salary = salary;
		this.address = address;
	}

	public int getEmployeeId() {
		return employeeId;
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

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
