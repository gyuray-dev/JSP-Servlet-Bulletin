package com.gyuray.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyuray.dao.PostDao;
import com.gyuray.dto.Post;

@WebServlet("/detail")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		PostDao dao = new PostDao();
		Post post = null;
		if(request.getMethod().equals("GET")) { // 리스트에서 제목을 클릭한 경우 조회수 증가
			post = dao.getPost(id);
		} else if(request.getMethod().equals("POST")) { // 기존 글을 수정하여 제출한 경우 조회수 미증가
			post = dao.getPost(id, false);
		}
		request.setAttribute("post", post);
		
		RequestDispatcher dp = request.getRequestDispatcher("./WEB-INF/jsp/detail.jsp");
		dp.forward(request, response);
	}
}
