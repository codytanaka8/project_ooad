package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;

public class Bill {
	
	private int id;
	private int employeeId;
	private int patientId;
	private Date createdAt;
	private String paymentType;
	private String status;
	private Connect con = Connect.getConnection();

	public Bill() {
		// TODO Auto-generated constructor stub
	}

	public Bill(int id, int employeeId, int patientId, Date createdAt, String paymentType, String status) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.patientId = patientId;
		this.createdAt = createdAt;
		this.paymentType = paymentType;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	private Bill map(ResultSet rs) {
		try {
			int id = rs.getInt("Bill_ID");
			int employeeId = rs.getInt("EmployeeID");
			int patientId = rs.getInt("PatientID");
			Date createdAt = rs.getDate("DatetimeCreated");
			String paymentType = rs.getString("PaymentType");
			String status = rs.getString("Status");
			return new Bill(id, employeeId, patientId, createdAt, paymentType, status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
	}

}
