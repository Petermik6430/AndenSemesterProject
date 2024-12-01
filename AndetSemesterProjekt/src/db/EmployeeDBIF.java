package db;

import model.Employee;

public interface EmployeeDBIF {
	public void createEmployee(int employeeId, String fName, String lName, String phoneNo, String mail, String cprNo,
							   double salary, String address);

	public void updateEmployee(Employee employee);

	public void deleteEmployee(int employeeId);

	Employee findEmployeeByPhoneNo(String phoneNo);


}
