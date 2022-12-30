package com.gyuray.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gyuray.dao.PostDao;
import com.gyuray.dto.Post;

@MultipartConfig(
	fileSizeThreshold=1024*1024,
	maxFileSize=1024*1024*5,
	maxRequestSize=1024*1024*5*5
)
@WebServlet("/editor")
public class EditorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// detail.jsp에서 편집 버튼 눌렀을 때
		PostDao dao = new PostDao();
		String id_ = request.getParameter("id");
		
		if(id_ != null && !id_.equals("")) {
			int id = Integer.parseInt(id_);
			Post post = dao.getPost(id, false);
			request.setAttribute("post", post);
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("./WEB-INF/jsp/editor.jsp");
		dp.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// editor.jsp에서 글 수정 후 제출 버튼 눌렀을 때 실행
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/http; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		PostDao dao = new PostDao();
		int id = Integer.parseInt(request.getParameter("id"));			
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String userName = request.getParameter("userName");

		
		//파일 받는 부분
		Part filePart = request.getPart("files");
		String fileName = filePart.getSubmittedFileName();
		
		String realPath = request.getServletContext().getRealPath("/upload");
		String filePath = realPath + File.separator + fileName;
		
		InputStream fis = filePart.getInputStream();
		FileOutputStream fos = new FileOutputStream(filePath);
		
		byte[] buf = new byte[1024];
		int size = 0;
		while((size = fis.read(buf)) != -1) {
			fos.write(buf, 0, size);
		}
		
		fos.close();
		fis.close();
		
		// 포스트
		Post post = new Post(title, userName, content, fileName);
		if(id == -1) { // 새 글을 작성한 경우
			dao.addPost(post);
			RequestDispatcher dp = request.getRequestDispatcher("list");
			dp.forward(request, response);
		} else { // 기존 글을 수정한 경우
			post.setId(id);
			dao.updatePost(post);
			RequestDispatcher dp = request.getRequestDispatcher("detail");
			dp.forward(request, response);
		}
	}
}