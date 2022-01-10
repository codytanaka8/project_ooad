package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class BillDetail {
	
	private int id;
	private int billId;
	private int medId;
	private int qty;
	private Connect con = Connect.getConnection();
	
	public BillDetail() {
		
	}

	public BillDetail(int id, int billId, int medId, int qty) {
		super();
		this.id = id;
		this.billId = billId;
		this.medId = medId;
		this.qty = qty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getMedId() {
		return medId;
	}

	public void setMedId(int medId) {
		this.medId = medId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	private BillDetail map(ResultSet rs) {
		try {
			int id = rs.getInt("BillDetail_ID");
			int billId = rs.getInt("Bill_ID");
			int medId = rs.getInt("MedicineID");
			int qty = rs.getInt("Quantity");
			return new BillDetail(id, billId, medId, qty);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
	}
	
	public Vector<BillDetail> getAllDetailById(){
		String query = String.format("SELECT * FROM bill_detail WHERE Bill_ID=?");
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = null;
		Vector<BillDetail> bills = new Vector<>();
		
		try {
			ps.setInt(1, billId);
			rs = ps.executeQuery();
			while(rs.next()) {
				BillDetail bill = map(rs);
				bills.add(bill);
			}
			return bills;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
