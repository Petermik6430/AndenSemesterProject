package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.Employee;

public class EmployeeDB implements EmployeeDBIF{

	    private DBConnection dbc;
	    private String FIND_BY_ID_SQL = "SELECT * FROM Employee WHERE employeeId=?";
	    private PreparedStatement ps_findById;

	    public EmployeeDB(DBConnection dbc) {
	        this.dbc = dbc;
	    }

	    public void createEmployee(int employeeId, String fName, String lName, String phoneNo, String mail, String cprNo, double salary, String address, String contactPersonPhoneNo) {
	       
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
	}


