package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

public class EmployeeDB implements EmployeeDBIF {

    private DBConnection dbc;
    private Connection con;
    
    private String FIND_EMPLOYEE_BY_ID = "select * from Employee where id = ?";
    private String FIND_ALL_EMPLOYEES = "select  * from Employee";
    
    private PreparedStatement ps_findEmployeeById;
    private PreparedStatement ps_findAllEmployees;

    public EmployeeDB() throws DataAccessException {
        init();
    }

    private void init() throws DataAccessException {
        dbc = DBConnection.getInstance();
        con = DBConnection.getInstance().getConnection();
        try {
            ps_findAllEmployees = con.prepareStatement(FIND_ALL_EMPLOYEES);
            ps_findEmployeeById = con.prepareStatement(FIND_EMPLOYEE_BY_ID);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to prepare statements", e);
        }
        
        
    }
    
    public Employee findEmployeeById(int id) throws DataAccessException {
        Employee emp = null;
        try {
            ps_findEmployeeById.setInt(1, id);
            ResultSet rs = ps_findEmployeeById.executeQuery();
            if (rs.next()) {
                emp = buildObject(rs);
            } 
        } catch (SQLException e) {
            throw new DataAccessException("Failed to find employee by id: " + id, e);
        }
        return emp;
    }
    

    public List<Employee> findAllEmployees() throws DataAccessException {
        List<Employee> employees = new ArrayList<>();
        try (ResultSet rs = ps_findAllEmployees.executeQuery()) {
            while (rs.next()) {
                Employee employee = buildObject(rs);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to find all employees", e);
        }
        return employees;
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
        } catch (SQLException e) {
            throw new DataAccessException("Failed to build employee object", e);
        }
        return emp;
    }



    public int createEmployee() {
		return 0;
    	
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
            throw new DataAccessException("Failed to delete employee", e);
        }
    }





}