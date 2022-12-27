package com.gyuray.dto;

public class PostView extends Post {
	int commentsCount;
	
	public PostView() {
	}
	
	public PostView(int id, String title, String userName, String regDate, int hit, int commentCount) {
		super(id, title, userName, regDate, hit, "");
		this.commentsCount = commentCount;
	}

	public PostView(int id, String title, String userName, String regDate, int hit, String content, int commentCount) {
		super(id, title, userName, regDate, hit, content);
		this.commentsCount = commentCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
}
