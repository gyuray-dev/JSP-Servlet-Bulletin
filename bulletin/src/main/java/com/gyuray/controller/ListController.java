package com.gyuray.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyuray.dao.PostDao;
import com.gyuray.dto.Post;

@WebServlet("/list")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PostDao dao = new PostDao();
		
		// 쿠키 검사해서 listAmount 확인
		String listAmount_ = "10";
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("listAmount")) {
					listAmount_ = cookie.getValue();
				}
			}
		}
		
		int listAmount = 0;
		if(!listAmount_.equals("")) {
			listAmount = Integer.parseInt(listAmount_);
		} else {
			listAmount = 10;
		}

		System.out.println("listAmount="+listAmount);
		request.setAttribute("listAmount", listAmount);
		

		
		// 마지막 페이지 계산
		int lastPage = (int) Math.ceil(dao.size() / (double) listAmount);
		request.setAttribute("lastPage", lastPage);
		
		// pager
		String p_ = request.getParameter("p");
		int p = 1;
		if(p_ != null && !p_.equals("")) {
			p = Integer.parseInt(p_);
			if(p < 1) { // pager left button 예외처리
				p = 1;
			} else if (p > lastPage){ // page right button 예외처리
				p = lastPage;
			}
		}
		
		// 목록 얻기
		List<Post> posts = dao.getPosts(p, listAmount);
		request.setAttribute("posts", posts);
		request.setAttribute("p", p);
		
		RequestDispatcher dp = request.getRequestDispatcher("./WEB-INF/jsp/index.jsp");
		dp.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
