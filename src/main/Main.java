package src.main;

import java.awt.EventQueue;

import src.controller.MedController;
import src.controller.PatientController;
import src.view.HrView;


//import controllers.UserController;

public class Main {

	public Main() {
		//UserController.getInstance().showLoginForm();
		//BillController.getInstance().showAdminView();

//		new HrView();
//		LoginController.getInstance().showLoginForm();

		//LoginController.getInstance().showLoginForm();
		MedController.getInstance().showPharmacistView();
//		PatientController.getInstance().showDoctorView();

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
