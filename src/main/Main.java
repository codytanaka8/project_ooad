package main;

import java.awt.EventQueue;

import controller.LoginController;
import controller.PatientController;


public class Main {

	public Main() {
		
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
