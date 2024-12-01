package db;

import model.Employee;

public interface EmployeeDBIF {
    void createEmployee(int employeeId, String fName, String lName, String phoneNo, String mail, String cprNo, 
    					double salary, String address);
    void updateEmployee(Employee employee); 
    void deleteEmployee(int employeeId);
    Employee findEmployeeByEmployee(int employeeId);
}
