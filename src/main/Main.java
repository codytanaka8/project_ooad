package main;

import java.awt.EventQueue;

import controller.BillController;

//import controllers.UserController;

public class Main {

	public Main() {
//		UserController.getInstance().showLoginForm();
		BillController.getInstance().showAdminView();
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
