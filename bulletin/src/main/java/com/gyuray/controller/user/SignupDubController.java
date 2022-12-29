package com.gyuray.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyuray.dao.UserDao;

@WebServlet("/isdup")
public class SignupDubController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String column = request.getParameter("column");
		String value = request.getParameter("value");
		UserDao dao = new UserDao();
		String result = "";
		if(dao.isDup(column, value)) {
			result = "true";
		}
		response.getWriter().write(result);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		
		UserDao dao = new UserDao();
		String result = "true";
		if(dao.areDup(userId, userName, userEmail)) {
			result ="";
		}
		response.getWriter().write(result);
	}

}
