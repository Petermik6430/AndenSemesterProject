package controller;

import db.DataAccessException;
import db.EmployeeDB;
import db.EmployeeDBIF;
import model.Employee;

public class EmployeeController {
	private EmployeeDBIF employeeDB;

	public EmployeeController() {
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
}
