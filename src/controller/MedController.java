package src.controller;

import java.util.Vector;

import src.model.Medicine;
import src.view.PharmacistView;
import src.view.AdminView;


public class MedController {
	public static MedController controller = null;
	private Medicine med;
	private String errorMsg = "";
	
	private MedController() {
		med = new Medicine();
	}
	
	public static MedController getInstance() {
		if(controller == null) {
			controller = new MedController();
			
		}
		return controller;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void showPharmacistView() {
		new PharmacistView();
	}
	
	public Medicine getMed(String name) {
		return med.getMed(name);
	}
	public Medicine getMed(int id) {
		return med.getMed(id);
	}
	
	
	public Vector<Medicine> getAll(){
		return med.getAll();
	}
	
	public String getErrorMessage() {
		return errorMsg;
	}
	
	public boolean insert( String name, int price, int qty) {
		//validation
		
		if(name.isEmpty()) {
			errorMsg = "Name and id must be filled!";
			return false;
			
		}else if(qty<0 ){
			
		return false;
			
		}else {
			med = new Medicine(0, name, price, qty); 
			boolean inserted = med.insert();
			
			if(inserted == false) {
				errorMsg = "Insert Failed";
				
			}
			
			return inserted;
			}
	}
	
	public boolean update(String id, String name, int price, int qty) {
		//validation
		int tempid = -1;
		if(id.isEmpty() && name.isEmpty()) {
			errorMsg = "Name and id must be filled!";
			return false;
			
		}else if(qty<0 ){
			
		return false;
			
		}else {
			try {
				tempid = Integer.parseInt(id);
				
			} catch (Exception e) {
				
				errorMsg ="Id must be numeric!";
				return false;	

			}
				
			}
		 
			med = new Medicine(tempid, name, price, qty); 
			boolean updated = med.update();
			
			if(updated == false) {
				errorMsg = "update Failed";
				
			}
			
			return updated;
		}
	
	public boolean delete( String medId) {
		//validation
		
		int testId = -1;
		
		try {
			testId = Integer.parseInt(medId);
			
		} catch (Exception e) {
			errorMsg = "Id must be numeric!";
			return false;
		
		}
		
		med = new Medicine();
		med.setId(testId);
		
		boolean deleted = med.delete();
		
		if(!deleted) {
			errorMsg = "Delete Failed!";
			
		}
		return deleted;
		
	}
	
	
	
}
