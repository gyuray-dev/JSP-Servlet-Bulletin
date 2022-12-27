package com.gyuray.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyuray.dao.UserDao;

@WebServlet("/signup")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/signup.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId_ = request.getParameter("userId");
		String userPassword_ = request.getParameter("userPassword");
		String userName_ = request.getParameter("userName");
		String userEmail_ = request.getParameter("userEmail");
		
		String userId = "";
		if(userId_ != null && !userId_.equals("")) {
			userId = userId_;
		}
		String userPassword = "";
		if(userPassword_ != null && !userPassword_.equals("")) {
			userPassword = userPassword_;
		}
		String userName = "";
		if(userName_ != null && !userName_.equals("")) {
			userName = userName_;
		}
		String userEmail = "";
		if(userEmail_ != null && !userEmail_.equals("")) {
			userEmail = userEmail_;
		}
		
		UserDao dao = new UserDao();
		dao.addUser(userId, userName, userPassword, userEmail);
		
		response.sendRedirect("signin");
	}
}
