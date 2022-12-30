package com.gyuray.dto;

public class PostView extends Post {
	int commentsCount;
	
	public PostView() {
	}
	
	public PostView(int id, String title, String userName, String regDate, int hit, int commentCount, String files) {
		super(id, title, userName, regDate, hit, "", files);
		this.commentsCount = commentCount;
	}

	public PostView(int id, String title, String userName, String regDate, int hit, String content, int commentsCount, String files) {
		super(id, title, userName, regDate, hit, content, files);
		this.commentsCount = commentsCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
}
