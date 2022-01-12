package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class Medicine {

	private int id;
	private String name;
	private int price;
	private int stock;
	private Connect con = Connect.getConnection();
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Medicine(int id, String name, int price, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	
	
	
	private Medicine map(ResultSet rs) {
		try {
			int id = rs.getInt("MedicineID");
			String name = rs.getString("Name");
			int price = rs.getInt("Price");
			int stock = rs.getInt("Stock");
			

			return new Medicine(id, name, price, stock);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
	}
	
	public Vector<Medicine> getAll(){
		String query = String.format("SELECT * FROM users");
		ResultSet rs = con.executeQuery(query);
		Vector<Medicine> meds = new Vector<>();
		
		try {
			while(rs.next()) {
			Medicine med= map(rs);
			meds.add(med);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return meds;
		
	}
	
	public Medicine getMed(String name) {
		String query = String.format("SELECT * FROM users WHERE email=?");
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = null;
		try {
			ps.setString(1, name);
			rs = ps.executeQuery();
			Medicine med = null;
			
			//jika ada data dari query
			
			if(rs.first()) {
				// rs dari query -> object user
				med = map(rs);
				return med;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean insert() {
		//boolean kalau berhasil return true kalau gagal return false
		String query = String.format("INSERT INTO Medicine (name , price, stock) VALUES (?, ?, ?)");
		PreparedStatement ps = con.prepareStatement(query);
			
		try {
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setInt(3, stock);
			return ps.executeUpdate()==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
