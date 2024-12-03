package model;

public class Person {
 private String fName;
 private String lName;
 private String phoneNo;
 private String mail;

 public Person(String fName, String lName, String phoneNo, String mail) {
     this.fName = fName;
     this.lName = lName;
     this.phoneNo = phoneNo;
     this.mail = mail;
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
}

