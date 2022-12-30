package com.gyuray.dto;

public class Post {
	int id;
	String title;
	String userName;
	String regDate;
	int hit;
	String content;
	String files;
	
	public Post() {
		
	}
	
	public Post(String title, String userName, String content, String files) {
		this(0, title, userName, "", 0, content, files);
	}
	
	public Post(int id, String title, String userName, String regDate, int hit, String content, String files) {
		super();
		this.id = id;
		this.title = title;
		this.userName = userName;
		this.regDate = regDate;
		this.hit = hit;
		this.content = content;
		this.files = files;
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
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", userName=" + userName + ", regDate=" + regDate + ", hit="
				+ hit + ", content=" + content + ", files=" + files + "]";
	}
}
