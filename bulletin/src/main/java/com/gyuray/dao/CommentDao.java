package com.gyuray.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gyuray.dto.Comment;

public class CommentDao {
	
	static String dbUrl = "jdbc:mysql://localhost:3306/bulletindb?allowMultiQueries=true";
	static String dbUser ="bulletinuser";
	static String dbPw = "1234";
	static String driverName = "com.mysql.cj.jdbc.Driver";
	
	public List<Comment> getComments(int postId) {
		List<Comment> comments = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM comment_table WHERE postId=?";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int commentId = rs.getInt("commentId");
				int userId = rs.getInt("userId");
				String commentContent = rs.getString("commentContent");
				String regDate = rs.getString("regDate");
				comments.add(new Comment(commentId, postId, userId, commentContent, regDate));
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
		
		return comments;
	}
	
	public int addComment(int postId, int userId, String commentContent) {
		int insertCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO comment_table (postId, userId, commentContent) VALUES (?, ?, ?)";

		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postId);
			ps.setInt(2, userId);
			ps.setString(3, commentContent);
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
	
	public int getCommentCount(int postId) {
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) AS count FROM comment_table WHERE postId=?";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
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
		
		return count;
	}
	
	public int deleteComment(int commentId) {
		int deleteCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM comment_table WHERE commentId=?";

		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, commentId);
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
}
