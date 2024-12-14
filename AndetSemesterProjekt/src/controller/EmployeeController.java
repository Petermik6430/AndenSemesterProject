package controller;

import java.util.ArrayList;
import java.util.List;

import db.DataAccessException;
import db.EmployeeDB;
import db.EmployeeDBIF;
import model.Employee;

public class EmployeeController {
	private EmployeeDBIF employeeDB;

	public EmployeeController() throws DataAccessException {
		employeeDB = new EmployeeDB();
	}
	
	public Employee getEmployee(int id) throws DataAccessException {
		return employeeDB.findEmployeeById(id);	
	}
	

	public List<Employee> getEmployees() throws DataAccessException {
	    return employeeDB.findAllEmployees();
	}


	public int createEmployee() throws DataAccessException {
		return employeeDB.createEmployee();
	}

	public void updateEmployee(Employee employee) throws DataAccessException {
		employeeDB.updateEmployee(employee);
	}

	public void deleteEmployee(int id) throws DataAccessException {
		employeeDB.deleteEmployee(id);
	}

}

