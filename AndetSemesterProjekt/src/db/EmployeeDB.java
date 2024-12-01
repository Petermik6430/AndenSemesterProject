package db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.Employee;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class EmployeeDB implements EmployeeDBIF {

	private DBConnection dbc;
	private String FIND_BY_ID_SQL = "SELECT * FROM Employee WHERE employeeId=?";
	private PreparedStatement ps_findById;

	public EmployeeDB(DBConnection dbc) {
		this.dbc = dbc;
	}

	public void createEmployee(int employeeId, String fName, String lName, String phoneNo, String mail, String cprNo,
			double salary, String address) {
	}

	@Override
	public void updateEmployee(Employee employee) {
		try (Connection c = dbc.getConnection()) {
			String sql = "UPDATE Employees SET fName = ?, lName = ?, phoneNo = ?, mail = ?, cprNo = ?, salary = ?, address = ? WHERE employeeId = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getPhoneNo());
			ps.setString(4, employee.getMail());
			ps.setString(5, employee.getCprNo());
			ps.setDouble(6, employee.getSalary());
			ps.setString(7, employee.getAddress());
			ps.setInt(8, employee.getEmployeeId());
			ps.executeUpdate();
		} catch (SQLDataException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmployee(int employeeId) {
		try (Connection c = dbc.getConnection()) {
			String sql = "DELETE FROM Employees WHERE employeeId = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ps.executeUpdate();
		} catch (SQLDataException e) {
			e.printStackTrace();
		}
	}

	public Employee findEmployeeByEmployee(int employeeId) {
		return null;
	}

	private Employee buildObject(ResultSet rs) {
		return null;
	}

	private List<Employee> buildObjects(ResultSet rs) {
		return null;
	}


	@Override
	public Employee findEmployeeByPhoneNo(String phoneNo) {
		// TODO Auto-generated method stub
		return null;
	}
}




