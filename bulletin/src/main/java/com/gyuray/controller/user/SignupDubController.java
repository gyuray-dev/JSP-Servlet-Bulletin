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
		String resBool = "";
		if(dao.isDup(column, value)) {
			resBool = "true";
		}
		System.out.printf("col=%s, val=%s\n", column, value);
		response.getWriter().write(resBool);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
