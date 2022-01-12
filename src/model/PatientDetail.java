package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class PatientDetail {
	
	private int PatientID;
	private int PatientDetailID;
	private int EmployeeID;
	private String Symptom;
	private Date CheckDate;
	private Connect con = Connect.getConnection();

	public PatientDetail() {
		// TODO Auto-generated constructor stub
	}


	public PatientDetail(int patientID, int patientDetailID, int employeeID, String symptom, Date checkDate) {
		super();
		PatientID = patientID;
		PatientDetailID = patientDetailID;
		EmployeeID = employeeID;
		Symptom = symptom;
		CheckDate = checkDate;
	}


	public int getPatientID() {
		return PatientID;
	}


	public void setPatientID(int patientID) {
		PatientID = patientID;
	}


	public int getPatientDetailID() {
		return PatientDetailID;
	}


	public void setPatientDetailID(int patientDetailID) {
		PatientDetailID = patientDetailID;
	}


	public int getEmployeeID() {
		return EmployeeID;
	}


	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}


	public String getSymptom() {
		return Symptom;
	}


	public void setSymptom(String symptom) {
		Symptom = symptom;
	}


	public Date getCheckDate() {
		return CheckDate;
	}


	public void setCheckDate(Date checkDate) {
		CheckDate = checkDate;
	}
	
	private PatientDetail map(ResultSet rs) {
		try {
			int PatientID = rs.getInt("PatientID");
			int PatientDetailID = rs.getInt("PatientDetailId");
			int EmployeeID = rs.getInt("EmployeeID");
			String Symptom = rs.getString("Symptom");
			Date CheckDate = rs.getDate("CheckDate");
			return new PatientDetail(PatientID,PatientDetailID, EmployeeID, Symptom,CheckDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<PatientDetail> getAllPatientDetail(){
		String query = String.format("SELECT * FROM patient_detail");
		ResultSet rs = con.executeQuery(query);
		Vector<PatientDetail> pds = new Vector<>();
		try {
			while(rs.next()){
				PatientDetail pd = map(rs);
				pds.add(pd);
			}
			return pds;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addPatientDetail(){
		String query = String.format("INSERT INTO patient_detail (PatientID , PatientDetailID , EmployeeID, Symptom, CheckDate) VALUES (?, ?, ?, ?, ?)");
		PreparedStatement ps = con.prepareStatement(query);
			
		try {
			ps.setInt(1, PatientID);
			ps.setInt(2, PatientDetailID);
			ps.setInt(3, EmployeeID);
			ps.setString(4, Symptom);
			ps.setDate(5, CheckDate);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	

}
