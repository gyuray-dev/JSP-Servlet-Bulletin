package com.gyuray.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gyuray.dao.UserDao;
import com.gyuray.dto.User;

@WebServlet("/signin")
public class SigninController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/signin.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectURL = "signin";
		
		String userId_ = request.getParameter("userId");
		String userPassword_ = request.getParameter("userPassword");
		
		String userId = "";
		if(userId_ != null && !userId_.equals("")) {
			userId = userId_;
		}
		String userPassword = "";
		if(userPassword_ != null && !userPassword_.equals("")) {
			userPassword = userPassword_;
		}
		
		UserDao dao = new UserDao();
		User user = dao.getUser(userId);
		if(user != null && user.getUserPassword().equals(userPassword)) { // 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("userNum",  user.getUserNum());
			redirectURL = "list";
		}
		
		response.sendRedirect(redirectURL);
	}

}
