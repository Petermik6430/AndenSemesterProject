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
    private String FIND_BY_ID_SQL = "SELECT * FROM Employee WHERE id = ?";
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
            ps_findById = con.prepareStatement(FIND_BY_ID_SQL);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to prepare statements", e);
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
            throw new DataAccessException("Failed to delete employee", e);
        }
    }

    public Employee findEmployeeById(int id) throws DataAccessException {
        Employee emp = null;
        dbc.startTransaction();
        try {
            ps_findById.setInt(1, id);
            ResultSet rs = ps_findById.executeQuery();
            if (rs.next()) {
                emp = buildObject(rs);
            } 
            dbc.commitTransaction();
        } catch (SQLException e) {
            dbc.rollbackTransaction();
            throw new DataAccessException("Failed to find employee by id: " + id, e);
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
        } catch (SQLException e) {
            throw new DataAccessException("Failed to build employee object", e);
        }
        return emp;
    }

    private List<Employee> buildObjects(ResultSet rs) throws DataAccessException {
        List<Employee> employees = new ArrayList<>();
        try {
            while (rs.next()) {
                employees.add(buildObject(rs));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to build employee objects", e);
        }
        return employees;
    }

    @Override
    public Employee findEmployeeByPhoneNo(String phoneNo) {
        return null; // TODO
    }

    public List<Employee> findAllEmployees() throws DataAccessException {
        List<Employee> employees = new ArrayList<>();
        dbc.startTransaction();
        try (ResultSet rs = ps_findAllEmployees.executeQuery()) {
            while (rs.next()) {
                Employee employee = buildObjectEmployee(rs);
                employees.add(employee);
            }
            dbc.commitTransaction();
        } catch (SQLException e) {
            dbc.rollbackTransaction();
            throw new DataAccessException("Failed to find all employees", e);
        }
        return employees;
    }

    private Employee buildObjectEmployee(ResultSet rs) throws DataAccessException {
        Employee employee = new Employee();
        try {
            employee.setEmployeeId(rs.getInt(1));
            employee.setFirstName(rs.getString(2));
            employee.setLastName(rs.getString(3));
            employee.setPhoneNo(rs.getString(4));
            employee.setEmail(rs.getString(5));
            employee.setCprNo(rs.getString(6));
            employee.setSalary(rs.getDouble(7));
            employee.setAddress(rs.getString(8));
        } catch (SQLException e) {
            throw new DataAccessException("Failed to build employee object", e);
        }
        return employee;
    }
}

