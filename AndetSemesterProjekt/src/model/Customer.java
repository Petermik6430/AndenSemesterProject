package model;

public class Customer extends Person {
	private int customerId;

	public Customer(int customerId, String fName, String lName, String phoneNo, String email) {
		super(fName, lName, phoneNo, email);
		this.customerId = customerId;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
