package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

import java.sql.SQLException;

public class Employee {
	
	private int employeeId;
	private int roleId;
	private String name;
	private String password;
	private int salary;
	private String status;
	private Connect con = Connect.getConnection();
	

	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String username, String userPassword) {
		this.name = username;
		this.password = userPassword;
	}
	
	public Employee find() {
		String query = String.format("SELECT * FROM employee WHERE Username = ? AND Password = ?");
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.first()) {				
				Employee em = new Employee();
				em.name=rs.getString("Username");
				em.employeeId=rs.getInt("EmployeeID");
				return em;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public Employee(int employeeId, int roleId, String name, String password, int salary, String status) {
		super();
		this.employeeId = employeeId;
		this.roleId = roleId;
		this.name = name;
		this.password = password;
		this.salary = salary;
		this.status = status;
	
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Connect getCon() {
		return con;
	}


	public void setCon(Connect con) {
		this.con = con;
	}
	
	private Employee map(ResultSet rs) {
		try {
			int employeeId = rs.getInt("EmployeeID");
			int roleId = rs.getInt("RoleId");
			String name = rs.getString("Name");
			String password = rs.getString("Password");
			int salary = rs.getInt("Salary");
			String status = rs.getString("Status");
			return new Employee( employeeId, roleId, name, password,salary, status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
	}
	
	//get all employee
	public Vector<Employee> getAllEmployee(){
		String query = String.format("SELECT * FROM employee");
		ResultSet rs = con.executeQuery(query);
		Vector<Employee> users = new Vector<>();
		try {
			while(rs.next()){
				Employee employee = map(rs);
				users.add(employee);
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	//get employee
	public Employee getEmployee(int employeeId){
		String query = String.format("SELECT * FROM employee WHERE EmployeeID=?");
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = null;
		try {
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			Employee employee;
		
			if(rs.first()){
				employee = map(rs);
				return employee;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//insertemployee
	public boolean insertEmployee(){
		String query = String.format("INSERT INTO employee (RoleID , Name , Password, Salary, Status) VALUES (?, ?, ?, ?, ?)");
		PreparedStatement ps = con.prepareStatement(query);
			
		try {
			ps.setInt(1, roleId);
			ps.setString(2, name);
			ps.setString(3, password);
			ps.setInt(4, salary);
			ps.setString(5, status);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	//update employee
	public boolean updateEmployee(){
		String query = String.format("UPDATE Employee SET RoleID=? , Name=? , Password=? , Salary=? , Status=? WHERE employeeId=?");
		PreparedStatement ps = con.prepareStatement(query);
			
		try {
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setInt(3, salary);
			ps.setString(4, status);
			ps.setInt(4, employeeId);
			return ps.executeUpdate() ==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	//getdoctor list (incomplete)
	public Vector<Employee> getDoctorList(){
		String query = String.format("SELECT * FROM employee WHERE RoleId=");
		ResultSet rs = con.executeQuery(query);
		Vector<Employee> users = new Vector<>();
		try {
			while(rs.next()){
				Employee employee = map(rs);
				users.add(employee);
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
