package controller;

import java.util.Vector;

import model.Medicine;
import view.AdminView;
import view.PharmacistView;


public class MedController {
	public static MedController controller = null;
	private Medicine med;
	private String errorMsg = "";
	
	public MedController() {
//		med = new Medicine();
	}
	
	public static MedController getInstance() {
		if(controller == null) {
			controller = new MedController();
			
		}
		return controller;
	}
	
	public void showPharmacistView() {
		new PharmacistView();
	}
	
	public Medicine getMed(String name) {
		return med.getMed(name);
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
			
		}else {
			med = new Medicine(0, name, price, qty); 
			boolean inserted = med.insert();
			
			if(inserted == false) {
				errorMsg = "Insert Failed";
				
			}
			
			return inserted;
		}
	}
}
