package src.controller;

import java.sql.Date;
import java.util.Vector;

import src.model.Patient;
import src.model.PatientDetail;
import src.view.DoctorView;



public class PatientController {
	
	public static PatientController controller;
	private Patient patient;
	private PatientDetail patientDt;
	private String errorMsg = "";
	
	
	public PatientController() {
		// TODO Auto-generated constructor stub
	}
	
	public static synchronized PatientController getInstance() {
		if(controller == null) {
			controller = new PatientController();
		}
		return controller;
	}
	
	public void showDoctorView(){
		new DoctorView();
	}
	
	public String getErrorMessage() {
		return errorMsg;
	}
	
	//get all patient
	public Vector<Patient> getAllPatient(){
		return patient.getAllPatient();
	}
	
	
	//search patient
	public Vector<Patient> searchPatient(String Name){
		return patient.searchPatient(Name);
	}
	
	//add patient detail data (incomplete sort of)
	public boolean addPatientDetail(String PatientID,String EmployeeID,String Symptom){
		int patID,EmID = -1;
		if(Symptom.isEmpty()){
			errorMsg = "Symptom cannot be empty";
			return false;
		}else{
			try {
				patID = Integer.parseInt(PatientID);
				EmID = Integer.parseInt(EmployeeID);
			} catch (NumberFormatException e) {
				errorMsg = " Id must be numeric!";
				return false;
			}
		}
		
		java.util.Date date=new java.util.Date(); 
		patientDt = new PatientDetail(patID,0,EmID,Symptom,(Date) date);
		boolean inserted = patientDt.addPatientDetail();
		
		if(inserted == false){
			errorMsg = "Insert failed";
		}
		return inserted;
	
	}
	
	//get patient detail
	public Vector<PatientDetail> getPatientDetail(String PatientID){
		return patientDt.getAllPatientDetail(Integer.parseInt(PatientID));
	}
	
	//get Patient
	public Patient getPatient(String PatientID){
		return patient.getPatient(Integer.parseInt(PatientID));
	}
	
	//add patient data
	public boolean addPatient(String Name,Date DOB){
		if(Name.isEmpty()){
			errorMsg = "Name cannot be empty";
			return false;
		}
		else if(DOB == null){
			errorMsg = "Date cannot be empty";
			return false;
		}
		else{
			patient = new Patient( 0, Name, DOB);
			boolean inserted = patient.addPatient();
			
			if(inserted == false){
				errorMsg = "Insert failed";
			}
			return inserted;
		}

	}
	
	
	//updatepatient
	public boolean updatePatient(String PatientID, String Name,Date DOB){
		int id = -1;
		if(Name.isEmpty()){
			errorMsg = "Name cannot be empty";
			return false;
		}
		else if(DOB == null){
			errorMsg = "Date cannot be empty";
			return false;
		}
		else{
			try {
				 id = Integer.parseInt(PatientID);
			} catch (NumberFormatException e) {
				errorMsg = " Id must be numeric!";
				return false;
			}
		}
		
		patient =  new Patient(id,Name,DOB);
		boolean updated = patient.update();
		if(!updated)
			errorMsg = "Update failed!";
		
		return updated;
		
	}
	
	

}
