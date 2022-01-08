package controller;

import java.util.Vector;

import model.Bill;
import view.AdminView;

public class BillController {
	
	public static BillController controller;
	private Bill bill;

	private BillController() {
		
	}
	
	public static BillController getInstance() {
		if(controller == null) {
			controller = new BillController();
		}
		
		return controller;
	}
	
	public void showAdminView() {
		new AdminView();
	}
	
	public Vector<Bill> getAll(){
		return bill.getAll();
	}
	
	public boolean insert() {
		return false;
	}
	
	public boolean checkout() {
		return false;
	}

}
