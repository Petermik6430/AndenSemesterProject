package db;

import java.sql.SQLException;
import java.util.List;

import model.Employee;

public interface EmployeeDBIF {
	public void createEmployee(int employeeId, String fName, String lName, String phoneNo, String mail, String cprNo,
							   double salary, String address) throws DataAccessException ;

	public void updateEmployee(Employee employee) throws DataAccessException;

	public void deleteEmployee(int employeeId) throws DataAccessException;

	public Employee findEmployeeByPhoneNo(String phoneNo) throws DataAccessException;
	
	public Employee findEmployeeById(int id) throws DataAccessException;
	
	public List<Employee> findAllEmployees() throws DataAccessException;


}

/*package db;

import java.sql.SQLException;
import java.util.List;

import model.Employee;

public interface EmployeeDBIF {
	public void createEmployee(int employeeId, String fName, String lName, String phoneNo, String mail, String cprNo,
							   double salary, String address) throws DataAccessException ;

	public void updateEmployee(Employee employee) throws DataAccessException;

	public void deleteEmployee(int employeeId) throws DataAccessException;

	public Employee findEmployeeByPhoneNo(String phoneNo) throws DataAccessException;
	
	public Employee findEmployeeById(int id) throws DataAccessException;
	
	public List<Employee> findAllEmployees() throws DataAccessException;


}
*/
