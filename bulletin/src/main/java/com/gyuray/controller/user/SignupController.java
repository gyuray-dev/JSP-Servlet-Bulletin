package com.gyuray.controller.user;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
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
	private static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJWZsUjH18qsaYsrNFiGYTzbo1+glcUQK2DMNBAZWn6Z4emYULCPMXAZRsDQhpvGk3dd4p0OArs6GuUpN92oFPwh8BmC5Q9RX+dOqjXPDgHUUOG7PVQcHVr6KGEWCZdyNyoK0jT3bb3rZo5humE6DH3C/p6VjOAGHsbp0Vc9y35TAgMBAAECgYBp1Qe5ID+FzsD/xjnK4o4JuqvyK62oco7tdKjkNrtxUrWiRrHWpgZockk+gIapzyplyJJXXyEJiMqcEbPG6HCm9PMPXAS8ZB11PO9P85kmli4cV29AVop0A/S1Qha493ZYvWah14gv+dX5jbhOT79CJZeBdugMUPevqOFpQXXmiQJBAMjl71XZAReivapipDPT+t9Zpv/WZdf5x79ihW2FFnHL8aTanqvsuxYV80puXhvOZYiIBJVZU9iqL17C6fY00KUCQQC+oeGaxGS2PeXZro8iFjUOJtFN5L+IONSTc7eFLd53KmO6YUaKFgr/6LsB/sUMjbejmlOIAkbWL5LO2chjMimXAkEAgX1DkO/4dkWdPq+p6wYdU80ziP+mUVlcWI2mNvtrhhnQPVP0phhGVmv7juQooH+ejiwt2B3ln2fCdK/poZpVSQJBALzthMC0+TcgO0c02AgpSCWhVPhEx4nDAPKt0Wj9H6isazARLi62T+p2BbihfTrGzQQoc7aswvWNKos0jYO5+DsCQQDBp3wqXRDXb2k7oGYT2kRUa1wXzDHevpedfcW0CFizxGrw9DgzMZnk7jVEP3tCxkykXdCtSEx1/VYgXWq8fupC";
	private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVmbFIx9fKrGmLKzRYhmE826NfoJXFECtgzDQQGVp+meHpmFCwjzFwGUbA0IabxpN3XeKdDgK7OhrlKTfdqBT8IfAZguUPUV/nTqo1zw4B1FDhuz1UHB1a+ihhFgmXcjcqCtI0922962aOYbphOgx9wv6elYzgBh7G6dFXPct+UwIDAQAB";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("publicKey", PUBLIC_KEY);
		RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/signup.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId_ = request.getParameter("userId");
		String encryptedPassword_ = request.getParameter("encryptedPassword");
		String userName_ = request.getParameter("userName");
		String userEmail_ = request.getParameter("userEmail");
		
		String userId = "";
		if(userId_ != null && !userId_.equals("")) {
			userId = userId_;
		}
		String encryptedPassword = "";
		if(encryptedPassword_ != null && !encryptedPassword_.equals("")) {
			encryptedPassword = encryptedPassword_;
		}
		String userName = "";
		if(userName_ != null && !userName_.equals("")) {
			userName = userName_;
		}
		String userEmail = "";
		if(userEmail_ != null && !userEmail_.equals("")) {
			userEmail = userEmail_;
		}
		
		String decryptedPassword = decryptPassword(encryptedPassword);
		
		
		UserDao dao = new UserDao();
		dao.addUser(userId, userName, decryptedPassword, userEmail);
		
		response.sendRedirect("signin");
	}
	
	private String decryptPassword(String encryptedPassword) {
		String decryptedPassword = null;
		
		try {
            //평문으로 전달받은 개인키를 개인키객체
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] bytePrivateKey = Base64.getDecoder().decode(PRIVATE_KEY.getBytes());
			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
			PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
			
            //만들어진 개인키객체를 기반으로 암호화모드로 설정하는 과정
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			
            //암호문을 평문화하는 과정
            byte[] byteEncryptedData = Base64.getDecoder().decode(encryptedPassword.getBytes());
            byte[] byteDecryptedData = cipher.doFinal(byteEncryptedData);
            decryptedPassword= new String(byteDecryptedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return decryptedPassword;
	}
	
}
