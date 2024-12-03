package model;

public class Customer extends Person {
 private int customerId;

 public Customer(int customerId, String fName, String lName, String phoneNo, String mail) {
     super(fName, lName, phoneNo, mail);
     this.customerId = customerId;
 }

 public int getCustomerId() {
     return customerId;
 }
}
