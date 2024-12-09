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

	public void createEmployee(int employeeId, String fName, String lName, String phoneNo, String email, String cprNo,
			double salary, String address) throws DataAccessException {
		employeeDB.createEmployee(employeeId, fName, lName, phoneNo, email, cprNo, salary, address);
	}

	public void updateEmployee(Employee employee) throws DataAccessException {
		employeeDB.updateEmployee(employee);
	}

	public void deleteEmployee(int employeeId) throws DataAccessException {
		employeeDB.deleteEmployee(employeeId);
	}

	public Employee findEmployeeByPhoneNo(String phoneNo) throws DataAccessException {
		return employeeDB.findEmployeeByPhoneNo(phoneNo);
	}
	
	public Employee getEmployee(int id) throws DataAccessException {
		return employeeDB.findEmployeeById(id);	
	}
	
//	public List<Employee>findAllEmployees() throws DataAccessException{
//		return employeeDB.findAllEmployees();	
//	}
	
	public List<Employee> getEmployees() throws DataAccessException {
	    try {
	        List<Employee> employees = employeeDB.findAllEmployees();
	        if (employees == null) {
	            employees = new ArrayList<>(); // Sørg for, at listen er initialiseret
	        }
	     //   return employees;
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	     //   return new ArrayList<>(); // Returner en tom liste i tilfælde af fejl
	    }
	    return employeeDB.findAllEmployees();
	}

}
