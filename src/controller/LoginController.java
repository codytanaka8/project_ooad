package src.controller;

import src.view.LoginView;

public class LoginController {
	
	public static LoginController controller;
	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	public void showLoginForm() {
		new LoginView();
	}
	
	public static LoginController getInstance() {
		if(controller == null) {
			controller = new LoginController();
		}
		
		return controller;
	}

}
