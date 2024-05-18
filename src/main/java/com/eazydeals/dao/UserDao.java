package com.eazydeals.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eazydeals.entities.User;

public class UserDao {

	private Connection con;

	public UserDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean saveUser(User user) {
		boolean flag = false;

		try {
			String query = "INSERT INTO [user](name, email, password, phone, gender, address, city, pincode, state) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getUserEmail());
			psmt.setString(3, user.getUserPassword());
			psmt.setString(4, user.getUserPhone());
			psmt.setString(5, user.getUserGender());
			psmt.setString(6, user.getUserAddress());
			psmt.setString(7, user.getUserCity());
			psmt.setString(8, user.getUserPincode());
			psmt.setString(9, user.getUserState());

			psmt.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public User getUserByEmailPassword(String userEmail, String userPassword) {
		User user = null;
		try {
			String query = "SELECT * FROM [user] WHERE email = ? AND password = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, userEmail);
			psmt.setString(2, userPassword);

			ResultSet set = psmt.executeQuery();
			while (set.next()) {
				user = new User();

				user.setUserId(set.getInt("userid"));
				user.setUserName(set.getString("name"));
				user.setUserEmail(set.getString("email"));
				user.setUserPassword(set.getString("password"));
				user.setUserPhone(set.getString("phone"));
				user.setUserGender(set.getString("gender"));
				user.setDateTime(set.getTimestamp("registerdate"));
				user.setUserAddress(set.getString("address"));
				user.setUserCity(set.getString("city"));
				user.setUserPincode(set.getString("pincode"));
				user.setUserState(set.getString("state"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();
		try {
			String query = "SELECT * FROM [user]";
			Statement statement = this.con.createStatement();
			ResultSet set = statement.executeQuery(query);
			while (set.next()) {
				User user = new User();
				user.setUserId(set.getInt("userid"));
				user.setUserName(set.getString("name"));
				user.setUserEmail(set.getString("email"));
				user.setUserPassword(set.getString("password"));
				user.setUserPhone(set.getString("phone"));
				user.setUserGender(set.getString("gender"));
				user.setDateTime(set.getTimestamp("registerdate"));
				user.setUserAddress(set.getString("address"));
				user.setUserCity(set.getString("city"));
				user.setUserPincode(set.getString("pincode"));
				user.setUserState(set.getString("state"));
				
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void updateUserAddresss(User user) {
		try {
			String query = "UPDATE [user] SET address = ?, city = ?, pincode = ?, state = ? WHERE userid = ?";
                        PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, user.getUserAddress());
			psmt.setString(2, user.getUserCity());
			psmt.setString(3, user.getUserPincode());
			psmt.setString(4, user.getUserState());
			psmt.setInt(5, user.getUserId());

			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateUserPasswordByEmail(String password, String mail) {
		try {
			String query = "UPDATE [user] SET password = ? WHERE email = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, password);
			psmt.setString(2, mail);
			
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		try {
			String query = "UPDATE [user] SET name = ?, email = ?, phone = ?, gender = ?, address = ?, city = ?, pincode = ?, state = ? WHERE userid = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getUserEmail());
			psmt.setString(3, user.getUserPhone());
			psmt.setString(4, user.getUserGender());
			psmt.setString(5, user.getUserAddress());
			psmt.setString(6, user.getUserCity());
			psmt.setString(7, user.getUserPincode());
			psmt.setString(8, user.getUserState());
			psmt.setInt(9, user.getUserId());

			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int userCount() {
		int count = 0;
		try {
			String query = "SELECT COUNT(*) FROM [user]";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public String getUserAddress(int uid) {
		String address = "";
		try {
			String query = "SELECT address, city, pincode, state FROM [user] WHERE userid = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, uid);

			ResultSet rs = psmt.executeQuery();
			rs.next();
			address = rs.getString(1) + ", " + rs.getString(2) + "-" + rs.getString(3) + ", " + rs.getString(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}
	public String getUserName(int uid) {
		String name = "";
		try {
			String query = "SELECT name FROM [user] WHERE userid = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, uid);
			
			ResultSet rs = psmt.executeQuery();
			rs.next();
			name = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	public String getUserEmail(int uid) {
		String email = "";
		try {
			String query = "SELECT email FROM [user] WHERE userid = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, uid);
			
			ResultSet rs = psmt.executeQuery();
			rs.next();
			email = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}
	public String getUserPhone(int uid) {
		String phone = "";
		try {
			String query = "SELECT phone FROM [user] WHERE userid = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, uid);
			
			ResultSet rs = psmt.executeQuery();
			rs.next();
			phone = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return phone;
	}
	public void deleteUser(int uid) {
		try {
			String query = "DELETE FROM [user] WHERE userid = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, uid);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<String> getAllEmail() {
		List<String> list = new ArrayList<>();
		try {
			String query = "SELECT email FROM [user]";
			Statement statement = this.con.createStatement();
			ResultSet set = statement.executeQuery(query);
			while (set.next()) {
				list.add(set.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
