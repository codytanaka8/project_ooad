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
	public static PatientController pcon;
	public static EmployeeController econ;
	public static MedController mcon;
	private model.Bill bill;
	private model.BillDetail bd;
	private String errorMsg = "";

	private BillController() {
		bill = new Bill();
		bd = new BillDetail();
		pcon = PatientController.getInstance();
		econ = EmployeeController.getInstance();
		mcon = MedController.getInstance();
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
		int idInt = -1;
		try {
			idInt = Integer.parseInt(id);
			new BillDetailView(""+idInt);
		} catch (NumberFormatException e) {
			errorMsg = "No data";
		}
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
	
	public boolean insert(String strEmployeeId, String strPatientId, String paymentType) {
		Date createdAt = Date.valueOf(LocalDate.now());
		int employeeId, patientId = -1;
		try {
			employeeId = Integer.parseInt(strEmployeeId);
			patientId = Integer.parseInt(strPatientId);
		} catch (NumberFormatException e) {
			errorMsg = "Employee and Patient ID must be integer!";
			return false;
		}
		
		if(econ.getEmployee(employeeId) == null) {
			errorMsg = "Employee does not exist!";
			return false;
		}
		else if(pcon.getPatient(strPatientId)==null) {
			errorMsg = "Patient does not exist!";
			return false;
		}
		else if(!(paymentType.equals("Cash") || paymentType.equals("Credit"))) {
			errorMsg = "Payment Type must be Cash or Credit!";
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
		
		if(mcon.getMed(medId)==null) {
			errorMsg = "Medicine does not exist!";
			return false;
		}
		if(!(qty>0)) {
			errorMsg = "Medicine quantity must be more than 0!";
			return false;
		}
		
		bd = new BillDetail(0, billId, medId, qty);
		boolean inserted = bd.insert();
		
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
		
		if(mcon.getMed(medId)==null) {
			errorMsg = "Medicine does not exist!";
			return false;
		}
		if(!(qty>0)) {
			errorMsg = "Medicine quantity must be more than 0!";
			return false;
		}
		
		bd = new BillDetail(detailId, 0, medId, qty);
		boolean updated = bd.update();
		
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
		
		bd = new BillDetail(id, 0, 0, 0);
		boolean deleted = bd.delete();
		
		if(!deleted) errorMsg = "Delete failed!";
		
		return deleted;
	}
	
}
