package com.gyuray.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gyuray.dto.User;

public class UserDao {
	
	static String dbUrl = "jdbc:mysql://localhost:3306/bulletindb?allowMultiQueries=true";
	static String dbUser ="bulletinuser";
	static String dbPw = "1234";
	static String driverName = "com.mysql.cj.jdbc.Driver";
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user_table";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String userId = rs.getString("userId");
				String userName = rs.getString("userName");
				String userPassword = rs.getString("userPassword");
				String userEmail = rs.getString("userEmail");
				users.add(new User(userId, userName, userPassword, userEmail));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return users;
	}
	
	public User getUser(String userId) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user_table WHERE userId=?";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int userNum = rs.getInt("userNum");
				String userName = rs.getString("userName");
				String userPassword = rs.getString("userPassword");
				String userEmail = rs.getString("userEmail");
				String userRegDate = rs.getString("userRegDate");
				user = new User(userNum, userId, userName, userPassword, userEmail, userRegDate);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	public int addUser(String userId, String userName, String userPassword, String userEmail) {
		int insertCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO user_table (userId, userName, userPassword, userEmail) VALUES (?, ?, ?, ?)";

		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, userName);
			ps.setString(3, userPassword);
			ps.setString(4, userEmail);
			insertCount = ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return insertCount;
	}
	
	public int deleteUser(int userId) {
		int deleteCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM user_table WHERE userId=?";

		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			deleteCount = ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return deleteCount;
	}

	public boolean isDup(String column, String value) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user_table WHERE " + column + "=?";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setString(1, value);
			rs = ps.executeQuery();
			return rs.next();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
