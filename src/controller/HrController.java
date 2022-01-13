package src.controller;

import src.view.HrView;

public class HrController {

	public static HrController controller;
	public HrController() {
		// TODO Auto-generated constructor stub
	}

	public void showHrPage() {
		new HrView();
	}
	public static HrController getInstance() {
		if(controller == null) {
			controller = new HrController();
		}
		
		return controller;
	}
}
