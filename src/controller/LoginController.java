package controller;

import view.LoginView;

public class LoginController {
	
	public static LoginController controller;
	public LoginController() {
		
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
