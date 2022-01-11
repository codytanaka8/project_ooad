package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class Role {
	
	private int RoleID;
	private String Name;
	private Connect con = Connect.getConnection();

	
	public Role(int roleID, String name) {
		super();
		RoleID = roleID;
		Name = name;
	}
	
	
	public Role() {
		// TODO Auto-generated constructor stub
	}

	public int getRoleID() {
		return RoleID;
	}

	public void setRoleID(int roleID) {
		RoleID = roleID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	private Role map(ResultSet rs) {
		try {
			int id = rs.getInt("RoleID");
			String name = rs.getString("name");
			return new Role(id, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	public Role getRole(int roleID){
		String query = String.format("SELECT * FROM role WHERE roleID=?");
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = null;
		try {
			ps.setInt(1, roleID);
			rs = ps.executeQuery();
			Role role;
			//jika ada datanya
			if(rs.first()){
				//rs dari query -> jadi object user
				role = map(rs);
				return role;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Vector<Role> getAllRole(){
		String query = String.format("SELECT * FROM role");
		ResultSet rs = con.executeQuery(query);
		Vector<Role> roles = new Vector<>();
		try {
			while(rs.next()){
				Role role = map(rs);
				roles.add(role);
			}
			return roles;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	
}
