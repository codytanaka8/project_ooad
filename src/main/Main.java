package main;

import java.awt.EventQueue;

import controllers.UserController;

public class Main {

	public Main() {
//		UserController.getInstance().showLoginForm();
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
