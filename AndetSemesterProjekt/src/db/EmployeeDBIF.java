package db;

import java.sql.SQLException;
import java.util.List;

import model.Employee;

public interface EmployeeDBIF {
	
	public Employee findEmployeeById(int id) throws DataAccessException;
	
	public List<Employee> findAllEmployees() throws DataAccessException;
	
	public int createEmployee() throws DataAccessException ;

	public void updateEmployee(Employee employee) throws DataAccessException;

	public void deleteEmployee(int employeeId) throws DataAccessException;




}
