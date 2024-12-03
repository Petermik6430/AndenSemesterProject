package model;

public class Person {
 private String fName;
 private String lName;
 private String phoneNo;
 private String email;

 public Person(String fName, String lName, String phoneNo, String email) {
     this.fName = fName;
     this.lName = lName;
     this.phoneNo = phoneNo;
     this.email = email;
 }

 public Person() {
	 
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
 
 public void setFirstName (String fName) { 
	 this.fName = fName;
 }
 
 public void setLastName (String lName) {
	 this.lName = lName;
 }
 
 public void setPhoneNo (String phoneNo) {
	 this.phoneNo = phoneNo; 
 }
 
 public void setEmail (String email) {
	 this.email = email;
 }
 
}

