package model;

//Subclass Employee
public class Employee extends Person {
 private int employeeId;

 public Employee(int employeeId, String fName, String lName, String phoneNo, String mail) {
     super(fName, lName, phoneNo, mail);
     this.employeeId = employeeId;
 }

 public int getEmployeeId() {
     return employeeId;
 }

 public void setEmployeeId(int employeeId) {
     this.employeeId = employeeId;
 }
}

