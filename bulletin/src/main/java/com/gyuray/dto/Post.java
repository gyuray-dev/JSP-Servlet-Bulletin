package com.gyuray.dto;

public class Post {
	int id;
	String title;
	String userName;
	String regDate;
	int hit;
	String content;
	int commentsCount;
	
	public Post() {
		
	}
	
	public Post(String title, String userName, String content) {
		this.id = 0;
		this.title = title;
		this.userName = userName;
		this.regDate = " ";
		this.hit = 0;
		this.content = content;
	}
	
	public Post(int id, String title, String userName, String regDate, int hit, String content, int commentsCount) {
		this.id = id;
		this.title = title;
		this.userName = userName;
		this.regDate = regDate;
		this.hit = hit;
		this.content = content;
		this.commentsCount = commentsCount;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String UserName) {
		this.userName = UserName;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", userName=" + userName + ", regDate=" + regDate + ", hit="
				+ hit + ", content=" + content + ", commentsCount=" + commentsCount + "]";
	}
}
