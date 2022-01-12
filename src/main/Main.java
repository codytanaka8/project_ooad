package main;

import java.awt.EventQueue;

import controller.BillController;
import controller.LoginController;

public class Main {

	public Main() {
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
