package db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class EmployeeDB implements EmployeeDBIF {

	private DBConnection dbc;
	private Connection con;
	private String FIND_BY_ID_SQL = "SELECT * FROM Employee WHERE employeeId=?";
	private String FIND_ALL_EMPLOYEES_SQL = "SELECT * FROM Employee";
	private PreparedStatement ps_findById;
	private PreparedStatement ps_findAllEmployees;

	public EmployeeDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		dbc = DBConnection.getInstance();
		con = DBConnection.getInstance().getConnection();
		try {
			ps_findAllEmployees = con.prepareStatement(FIND_ALL_EMPLOYEES_SQL);
		} catch (SQLException e) {
			throw new DataAccessException("", e);
		}
		
	}

	public void createEmployee(int employeeId, String fName, String lName, String phoneNo, String email, String cprNo,
			double salary, String address) {
	}

	@Override
	public void updateEmployee(Employee employee) throws DataAccessException {
		try (Connection c = dbc.getConnection()) {
			String sql = "UPDATE Employees SET fName = ?, lName = ?, phoneNo = ?, email = ?, cprNo = ?, salary = ?, address = ? WHERE employeeId = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getPhoneNo());
			ps.setString(4, employee.getEmail());
			ps.setString(5, employee.getCprNo());
			ps.setDouble(6, employee.getSalary());
			ps.setString(7, employee.getAddress());
			ps.setInt(8, employee.getEmployeeId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmployee(int employeeId) throws DataAccessException {
		try (Connection c = dbc.getConnection()) {
			String sql = "DELETE FROM Employees WHERE employeeId = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("", e);
		}
	}

	public Employee findEmployeeById(int id) throws DataAccessException {
		Employee emp = null;
		dbc.startTransaction();
		
		try {
			ps_findById.setInt(1, id);
			ResultSet rs = ps_findById.executeQuery();
			rs.next();
			emp = buildObject(rs);
		} catch(SQLException e) {
			dbc.rollbackTransaction();
			throw new DataAccessException("fejl",e); //TODO skriv en beskrivende fejlbesked
		}
		return emp;
		
	}
	
	private Employee buildObject(ResultSet rs) throws DataAccessException {
		Employee emp = new Employee();
		try {
			emp.setEmployeeId(rs.getInt(1));
			emp.setFirstName(rs.getString(2));
			emp.setLastName(rs.getString(3));
			emp.setPhoneNo(rs.getString(4));
			emp.setEmail(rs.getString(5));
			emp.setCprNo(rs.getString(6));
			emp.setSalary(rs.getDouble(7));
			emp.setAddress(rs.getString(8));
			
			
		} catch(SQLException e) {
			throw new DataAccessException("fejl",e); //TODO skriv en beskrivende fejlbesked
		}
		return emp;
	}

	private List<Employee> buildObjects(ResultSet rs) {
		return null;
	}

	@Override
	public Employee findEmployeeByPhoneNo(String phoneNo) {
		return null; // TODO
	}

	public List<Employee> findAllEmployees() throws DataAccessException {
		List<Employee> employees = new ArrayList<>();
		dbc.startTransaction();
		try(ResultSet rs = ps_findAllEmployees.executeQuery()) {
			while(rs.next()) {
				Employee employee = buildObjectEmployee(rs);
				employees.add(employee);
			}
		} catch(SQLException e) {
			dbc.rollbackTransaction();
			throw new DataAccessException("fejl",e); //TODO skriv en beskrivende fejlbesked
		}
		return employees;
	}

	private Employee buildObjectEmployee(ResultSet rs) throws DataAccessException {
		Employee employee = new Employee();
		try {
			int id = rs.getInt("id");
			
		} catch(SQLException e) {
			throw new DataAccessException("fejl",e); //TODO skriv en beskrivende fejlbesked
		}
		return employee;
	}
}
