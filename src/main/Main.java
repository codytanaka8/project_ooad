package main;

import java.awt.EventQueue;

import controller.BillController;
import controller.LoginController;
import view.HrView;

import controller.MedController;
import controller.PatientController;


//import controllers.UserController;

public class Main {

	public Main() {
		//UserController.getInstance().showLoginForm();
		//BillController.getInstance().showAdminView();

		new HrView();
//		LoginController.getInstance().showLoginForm();

		//LoginController.getInstance().showLoginForm();
		//MedController.getInstance().showPharmacistView();
		PatientController.getInstance().showDoctorView();

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
