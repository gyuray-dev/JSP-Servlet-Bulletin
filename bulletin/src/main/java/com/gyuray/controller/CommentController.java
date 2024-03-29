package com.gyuray.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyuray.dao.CommentDao;

@WebServlet("/comment")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("_method") != null && request.getParameter("_method").equals("DELETE")) {
			doDelete(request, response);
			return;
		}
		
		CommentDao dao = new CommentDao();
		String postId_ = request.getParameter("postId");
		String commentContent_ = request.getParameter("commentContent");
		
		int postId = -1;
		if(postId_ != null && !postId_.equals("")) {
			postId = Integer.parseInt(postId_);
		}
		String commentContent = "";
		if(postId_ != null) {
			commentContent = commentContent_;
		}
		
		// userId는 회원 기능 구현 후 추가
		int userId = 0;
		
		dao.addComment(postId, userId, commentContent);
		
		RequestDispatcher dp = request.getRequestDispatcher("detail?id=" + postId);
		dp.forward(request, response);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentId_ = request.getParameter("commentId");
		String postId_ = request.getParameter("postId");
		
		int postId = -1;
		if(postId_ != null && postId_ != "") {
			postId = Integer.parseInt(postId_);
		}
		int commentId = -1;
		if(commentId_ != null && !commentId_.equals("")) {
			commentId = Integer.parseInt(commentId_);
		}
		
		CommentDao dao = new CommentDao();
		System.out.println(dao.deleteComment(commentId));
		
		RequestDispatcher dp = request.getRequestDispatcher("detail?id=" + postId);
		dp.forward(request, response);
	}
}
