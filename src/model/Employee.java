package model;

import connect.Connect;

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

}
