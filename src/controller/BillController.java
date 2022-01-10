package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Vector;

import model.Bill;
import model.BillDetail;
import view.AdminView;
import view.BillDetailView;

public class BillController {
	
	public static BillController controller;
	private Bill bill;
	private String errorMsg = "";

	private BillController() {
		
	}
	
	public String getErrorMsg() {
		return errorMsg;
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
	
	public void showBillDetailView(String id) {
		new BillDetailView(id);
	}
	
	public Vector<Bill> getAll(){
		return bill.getAll();
	}
	
	public Vector<BillDetail> getAllDetailById(String id){
		int idInt = -1;
		try {
			idInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			errorMsg = "No data";
			return null;
		}
		
		bill = new Bill(idInt, 0, 0, null, "", "");
		return bill.getBillDetail().getAllDetailById();
	}
	
	public boolean insert(int employeeId, String patientId, String paymentType) {
		Date createdAt = Date.valueOf(LocalDate.now());
		/*
		if(getEmployee(employeeId) == null) {
			errorMsg = "Employee does not exist!";
			return false;
		}
		else {
			bill = new Bill(0, employeeId, patientId, createdAt, paymentType, "Unpaid");
			boolean inserted = bill.insert();
			
			if(inserted == false) {
				errorMsg = "Insert failed!";
			}
			
			return inserted;
		}
		*/
		return false;
	}
	
	public boolean checkout(String id) {
		int idInt = -1;
		try {
			idInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			errorMsg = "ID must be numeric!";
			return false;
		}
		
		bill = new Bill(idInt, 0, 0, null, "", "Paid");
		boolean updated = bill.checkout();
		
		if(updated == false) {
			errorMsg = "Checkout failed!";
		}
		
		return updated;
	}

}
