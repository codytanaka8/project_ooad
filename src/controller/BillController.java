package controller;

import view.AdminView;

public class BillController {
	
	public static BillController controller;

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

}
