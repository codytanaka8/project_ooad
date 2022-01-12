package main;

import java.awt.EventQueue;

import controller.LoginController;

//import controllers.UserController;

public class Main {

	public Main() {
	//	UserController.getInstance().showLoginForm();
//		BillController.getInstance().showAdminView();
		LoginController.getInstance().showLoginForm();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Main();
			}
		});
	}

}
