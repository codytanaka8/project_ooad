package controller;


import java.util.Vector;

import model.Employee;


public class EmployeeController {
	private Vector<Employee> employeeList;
	private String errorMessage = "";
	private static EmployeeController controller = null;
	Employee employee;
	
	public boolean addEmployee(int roleID,String name,String username,String salary,String password,String status){
		int salarydigit=Integer.parseInt(salary);
		Employee em=new Employee(roleID, name, username, salarydigit,password, status);
		em=em.insertEmployee();
		
		if(em == null) {
			this.errorMessage = "Insert Employee Failed";
			return false;
		}
		return true;
	}
	
	public EmployeeController() {
		employee = new Employee();
	}
	
	public Vector<Employee> getAllEmployee(){
		return employee.getAllEmployee();
	}
	
	public static synchronized EmployeeController getInstance() {
		if(controller == null) {
			controller = new EmployeeController();
		}
		return controller;
	}
	
	public Employee authenticate(String username, String password) {
		if(username.isEmpty()) {
			errorMessage = "Username must be filled!";
			return null;
		}
		if(password.isEmpty()) {
			errorMessage = "Password must be filled!";
			return null;
		}
		
		Employee em = new Employee(username, password).find();
		if(em == null) {
			errorMessage = "Invalid username or password!";
		}
		else {
			BillController.getInstance().showAdminView();
		}
		return em;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean fireEmployee(int employeeID) {
		Employee em = new Employee();

		em.setEmployeeId(employeeID);
		boolean success = em.delete();
		if(success == false) {
			errorMessage = "Delete Failed";
		}
		return success;
		
	}

}
