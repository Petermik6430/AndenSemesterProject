package db;

import model.Employee;

public interface EmployeeDBIF {
    void createEmployee(int employeeId, String fName, String lName, String phoneNo, String mail, String cprNo, 
    					double salary, String address, String contactPersonPhoneNo);
    Employee findEmployeeByEmployee(int employeeId);
}
