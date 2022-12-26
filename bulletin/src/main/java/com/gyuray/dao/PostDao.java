package com.gyuray.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gyuray.dto.Post;

public class PostDao {
	static String dbUrl = "jdbc:mysql://localhost:3306/bulletindb?allowMultiQueries=true";
	static String dbUser ="bulletinuser";
	static String dbPw = "1234";
	static String driverName = "com.mysql.cj.jdbc.Driver";
	
	public PostDao() {

	}
	
	public int addPost(Post post) {
		int insertCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT "
				+ "INTO bulletin_table "
				+ "(title, userName, content) "
				+ "VALUES "
				+ "(?, ?, ?)";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setString(1, post.getTitle());
			ps.setString(2, post.getUserName());
			ps.setString(3, post.getContent());
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
	
	public Post getPost(int id) {
		// 게시글 조회 시 default true -> 조회수 증가
		return getPost(id, true);
	}
	
	public Post getPrevPost(int id) {		
		Post post = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (\n"
				+ "	SELECT * FROM bulletin_view ORDER BY id DESC) N\n"
				+ "WHERE id < ? LIMIT 1;";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int prevId = rs.getInt("id");
				String title = rs.getString("title");
				String userName = rs.getString("userName");
				String regDate = rs.getString("regDate");
				int hit = rs.getInt("hit");
				String content = rs.getString("content");
				int commentsCount = rs.getInt("commentsCount");
				post = new Post(prevId, title, userName, regDate, hit, content, commentsCount);
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
		
		return post;
	}
	
	public Post getNextPost(int id) {
		Post post = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bulletin_view WHERE id > ? LIMIT 1";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int nextId = rs.getInt("id");
				String title = rs.getString("title");
				String userName = rs.getString("userName");
				String regDate = rs.getString("regDate");
				int hit = rs.getInt("hit");
				String content = rs.getString("content");
				int commentsCount = rs.getInt("commentsCount");
				post = new Post(nextId, title, userName, regDate, hit, content, commentsCount);
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
		return post;
	}
	
	public Post getPost(int id, boolean bHit) {
		Post post = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bulletin_view WHERE id=?";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			
			// true 시 조회수 증가
			if(bHit) {
				String sql2 = (bHit)? "UPDATE bulletin_table SET hit=hit+1 WHERE id=?" :"";
				ps = conn.prepareStatement(sql2);
				ps.setInt(1, id);
				ps.executeUpdate();
				ps.close();
			}
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString("title");
				String userName = rs.getString("userName");
				String regDate = rs.getString("regDate");
				int hit = rs.getInt("hit");
				String content = rs.getString("content");
				int commentsCount = rs.getInt("commentsCount");
				post = new Post(id, title, userName, regDate, hit, content, commentsCount);
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
		
		return post;
	}
	
	public List<Post> getPosts() {
		return getPosts(1, 10, "title", "");
	}
	
	public List<Post> getPosts(int page, int amount, String searchType, String searchContent) {
		List<Post> posts = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "
				+ "(SELECT * FROM bulletin_view WHERE " + searchType + " LIKE ?) N "
				+ "ORDER BY id DESC LIMIT ? OFFSET ?;";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + searchContent + "%");
			ps.setInt(2, amount);
			ps.setInt(3, amount * (page - 1));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String userName = rs.getString("userName");
				String regDate = rs.getString("regDate");
				int hit = rs.getInt("hit");
				String content = rs.getString("content");
				int commentsCount = rs.getInt("commentsCount");
				posts.add(new Post(id, title, userName, regDate, hit, content, commentsCount));
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
		
		return posts;
	}

	public int size(String searchType, String searchContent) {
		int size = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) AS nums FROM bulletin_view WHERE " + searchType + " LIKE ? ";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + searchContent + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				size = rs.getInt("nums");
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
		
		return size;
	}
	
	public int updatePost(Post post) {
		int updateCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE bulletin_table SET title=?, content=? WHERE id=?";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setString(1, post.getTitle());
			ps.setString(2, post.getContent());
			ps.setInt(3, post.getId());
			updateCount = ps.executeUpdate();
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
		
		return updateCount;
	}

	public int deletePost(int id) {
		int deleteCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM bulletin_table WHERE id=?;"
				+ "DELETE FROM comment_table WHERE postid=?";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, id);
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
