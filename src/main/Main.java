package main;

import java.awt.EventQueue;

import controller.BillController;
import controller.HrController;
import controller.LoginController;
import view.AdminView;
import view.HrView;
import view.InsertEmployeeView;

//import controllers.UserController;

public class Main {

	public Main() {
//		new InsertEmployeeView();
		HrController.getInstance().showHrPage();
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
