package src.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import src.connect.Connect;

public class Patient {
	
	private int PatientID;
	private String Name;
	private Date DOB;
	private Connect con = Connect.getConnection();

	

	public Patient(int patientID, String name, Date dOB) {
		super();
		PatientID = patientID;
		Name = name;
		DOB = dOB;
	}


	public int getPatientID() {
		return PatientID;
	}


	public void setPatientID(int patientID) {
		PatientID = patientID;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public Date getDOB() {
		return DOB;
	}


	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	


	private Patient map(ResultSet rs) {
		try {
			int PatientID = rs.getInt("PatientID");
			String Name = rs.getString("Name");
			Date DOB = rs.getDate("DOB");
			return new Patient(PatientID, Name, DOB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	//addpatient
	public boolean addPatient() {
		String query = String.format("INSERT INTO patient ( Name ,DOB) VALUES (?, ?, ?)");
		PreparedStatement ps = con.prepareStatement(query);
			
		try {
			ps.setString(1, Name);
			ps.setDate(2, DOB);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//getallpatient
	public Vector<Patient> getAllPatient(){
		String query = String.format("SELECT * FROM employee");
		ResultSet rs = con.executeQuery(query);
		Vector<Patient> patients = new Vector<>();
		try {
			while(rs.next()){
				Patient patient = map(rs);
				patients.add(patient);
			}
			return patients;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	//searchpatient (incomplete)
	public Vector<Patient>  searchPatient(String Name){
		String query = String.format("SELECT * FROM patient WHERE Name=?");
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = con.executeQuery(query);
		Vector<Patient> patients = new Vector<>();
		try {
			ps.setString(1, Name);
			while(rs.next()){
				Patient patient = map(rs);
				patients.add(patient);
			}
			return patients;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//updatepatient
	public boolean update() {
		String query = String.format("UPDATE Users SET Name=? , DOB=? WHERE PatientID=?");
		PreparedStatement ps = con.prepareStatement(query);
			
		try {
			ps.setString(1, Name);
			ps.setDate(2, DOB);
			ps.setInt(4, PatientID);
			return ps.executeUpdate() ==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	//getpatient
	public Patient getPatient(int PatientID){
		String query = String.format("SELECT * FROM patient WHERE PatientID=?");
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = null;
		try {
			ps.setInt(1, PatientID);
			rs = ps.executeQuery();
			Patient patient;
			
			if(rs.first()){
				patient = map(rs);
				return patient;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
