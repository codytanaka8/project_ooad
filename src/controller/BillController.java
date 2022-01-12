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

	public boolean insertDetail(String strBillId, String strMedId, String strQty) {
		int billId, medId, qty = -1;
		try {
			billId = Integer.parseInt(strBillId);
			medId = Integer.parseInt(strMedId);
			qty = Integer.parseInt(strQty);
		} catch (NumberFormatException e) {
			errorMsg = "Insert failed! ID and Qty must be numeric!";
			return false;
		}
		
		bill = new Bill(billId, 0, 0, null, "", "");
		bill.getBillDetail().setMedId(medId);
		bill.getBillDetail().setQty(qty);
		boolean inserted = bill.getBillDetail().insert();
		
		if(!inserted) errorMsg = "Insert failed!";
		
		return inserted;
	}
	
	public boolean updateDetail(String id, String strMedId, String strQty) {
		int detailId, medId, qty = -1;
		try {
			detailId = Integer.parseInt(id);
			medId = Integer.parseInt(strMedId);
			qty = Integer.parseInt(strQty);
		} catch (NumberFormatException e) {
			errorMsg = "Update failed! ID and Qty must be numeric!";
			return false;
		}
		
		bill = new Bill(detailId, 0, 0, null, "", "");
		bill.getBillDetail().setId(detailId);
		bill.getBillDetail().setMedId(medId);
		bill.getBillDetail().setQty(qty);
		boolean updated = bill.getBillDetail().update();
		
		if(!updated) errorMsg = "Update failed!";
		
		return updated;
	}
	
	public boolean deleteDetail(String strDetailId) {
		int id = -1;
		try {
			id = Integer.parseInt(strDetailId);
		} catch (NumberFormatException e) {
			errorMsg = "ID must be numeric";
			return false;
		}
		
		bill = new Bill(0, 0, 0, null, "", "");
		bill.getBillDetail().setId(id);
		boolean deleted = bill.getBillDetail().delete();
		
		if(!deleted) errorMsg = "Delete failed!";
		
		return deleted;
	}
	
}
