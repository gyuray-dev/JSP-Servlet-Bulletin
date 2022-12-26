package com.gyuray.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyuray.dao.CommentDao;
import com.gyuray.dao.PostDao;
import com.gyuray.dto.Post;

@WebServlet("/detail")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 게시글 받아오기
		int postId = Integer.parseInt(request.getParameter("id"));
		
		PostDao postDao = new PostDao();
		Post post = null;
		if(request.getMethod().equals("GET")) { // 리스트에서 제목을 클릭한 경우 조회수 증가
			post = postDao.getPost(postId);
		} else if(request.getMethod().equals("POST")) { // 기존 글을 수정한 경우 조회수 미증가
			post = postDao.getPost(postId, false);
		}
		request.setAttribute("post", post);
		
		request.setAttribute("prevPost", postDao.getPrevPost(postId));
		request.setAttribute("nextPost", postDao.getNextPost(postId));
		
		CommentDao commentDao = new CommentDao();
		request.setAttribute("comments",commentDao.getComments(postId));
		
		
		RequestDispatcher dp = request.getRequestDispatcher("./WEB-INF/jsp/detail.jsp");
		dp.forward(request, response);
	}
}
