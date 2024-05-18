package com.eazydeals.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eazydeals.entities.Admin;

public class AdminDao {
	
	private Connection con;
	
	public AdminDao(Connection con) {
		super();
		this.con = con;
	}
	
	public boolean saveAdmin(Admin admin) {
		boolean flag = false;

		try {
			String query = "INSERT INTO [admin](name, email, password, phone) VALUES(?, ?, ?, ?)";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, admin.getName());
			psmt.setString(2, admin.getEmail());
			psmt.setString(3, admin.getPassword());
			psmt.setString(4, admin.getPhone());

			psmt.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public Admin getAdminByEmailPassword(String email, String password) {
		Admin admin = null;
		try {
			String query = "SELECT * FROM [admin] WHERE email = ? AND password = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, email);
			psmt.setString(2, password);

			ResultSet set = psmt.executeQuery();
			while (set.next()) {
				admin = new Admin();
				admin.setId(set.getInt("id"));
				admin.setName(set.getString("name"));
				admin.setEmail(set.getString("email"));
				admin.setPassword(set.getString("password"));
				admin.setPhone(set.getString("phone"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
	public List<Admin> getAllAdmin(){
		List<Admin> list = new ArrayList<Admin>();
		try {
			
			String query = "SELECT * FROM [admin]";
			Statement statement = this.con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setId(rs.getInt("id"));
				admin.setName(rs.getString("name"));
				admin.setEmail(rs.getString("email"));
				admin.setPhone(rs.getString("phone"));
				admin.setPassword(rs.getString("password"));
				
				list.add(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean deleteAdmin(int id) {
		boolean flag = false;
		try {
			String query = "DELETE FROM [admin] WHERE id = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
