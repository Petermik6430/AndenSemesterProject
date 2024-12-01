package db;

import java.sql.SQLException;

import model.Employee;

public interface EmployeeDBIF {
	public void createEmployee(int employeeId, String fName, String lName, String phoneNo, String mail, String cprNo,
							   double salary, String address) throws DataAccessException ;

	public void updateEmployee(Employee employee) throws DataAccessException;

	public void deleteEmployee(int employeeId) throws DataAccessException;

	Employee findEmployeeByPhoneNo(String phoneNo) throws DataAccessException;


}
