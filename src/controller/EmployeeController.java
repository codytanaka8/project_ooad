package controller;


import java.util.Vector;

import model.Employee;


public class EmployeeController {
	private Vector<Employee> employeeList;
	private String errorMessage = "";
	private static EmployeeController controller = null;
	Employee employee;
	public EmployeeController() {
		employee = new Employee();
	}
	
	public boolean updateEmployee(int employeeID,int roleID,String name,String salary){
		int salarydigit;
		try {
			salarydigit = Integer.parseInt(salary);
		} catch (NumberFormatException e) {
			this.errorMessage = "Salary must be numeric";
			return false;
		}
		
		if(name.length()<=0||salary.length()<=0){
			this.errorMessage = "Please fill all of the input";
			return false;
		}
		else if(salarydigit<=0){
			this.errorMessage = "Salary must be above 0";
			return false;
		}else if(roleID>99){
			this.errorMessage = "invalid role id";
			return false;
		}
		
		Employee em=new Employee();
		em=em.getEmployee(employeeID);
		em.setRoleId(roleID);
		em.setName(name);
		em.setSalary(salarydigit);
		em.updateEmployee();

		return true;
	}
	
	public boolean addEmployee(int roleID,String name,String username,String salary,String password,String status){
		int salarydigit;
		try {
			salarydigit = Integer.parseInt(salary);
		} catch (NumberFormatException e) {
			this.errorMessage = "Salary must be numeric";
			return false;
		}
		
		if(name.length()<=0||username.length()<=0||salary.length()<=0||password.length()<=0||status.length()<=0){
			this.errorMessage = "Please fill all of the input";
			return false;
		}
		else if(salarydigit<=0){
			this.errorMessage = "Salary must be above 0";
			return false;
		}else if(!status.equals("Active")&&!status.equals("Inactive")){
			this.errorMessage = "Status must only be Active or Inactive";
			return false;
		}
		
		Employee em=new Employee(roleID, name, username, salarydigit,password, status);
		em=em.insertEmployee();
		
		if(em == null) {
			this.errorMessage = "Insert Employee Failed";
			return false;
		}
		return true;
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
	public Vector<Employee> getAllEmployee(){
		return employee.getAllEmployee();
	}
	
	public Vector<Employee> getDoctorList(){
		return employee.getDoctorList();
	}
	
	public Employee getEmployee(int employeeId) {
		return employee.getEmployee(employeeId);
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
			if(em.getRoleId()==1) BillController.getInstance().showAdminView();
			//else if(em.getRoleId()==2) 
			else if(em.getRoleId()==3) PatientController.getInstance().showDoctorView();
			//else if(em.getRoleId()==4) PatientController.getInstance().showNurseView();
			else if(em.getRoleId()==5) HrController.getInstance().showHrPage();
		}
		return em;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

}
