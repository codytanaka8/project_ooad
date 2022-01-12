package controller;


import model.Employee;


public class EmployeeController {
	private String errorMessage = "";
	private static EmployeeController controller = null;
	public EmployeeController() {
		
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

}
