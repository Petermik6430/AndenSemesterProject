package controller;

import db.EmployeeDBIF;
import model.Employee;

public class EmployeeController {
    private EmployeeDBIF employeeDB;

    public EmployeeController(EmployeeDBIF employeeDB) {
        this.employeeDB = employeeDB;
    }

    public void createEmployee(int employeeId, String fName, String lName, String phoneNo, String mail, String cprNo, double salary, String address, String contactPersonPhoneNo) {
        employeeDB.createEmployee(employeeId, fName, lName, phoneNo, mail, cprNo, salary, address, contactPersonPhoneNo);
    }

    public Employee findEmployeeByPhoneNo(String phoneNo) {
        return null;
    }
}



