package com.bbs.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.userDao;


@WebServlet("/regServlet.do")
public class regServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userDao udao=new userDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
		String upass=request.getParameter("upass");
		String head=request.getParameter("head");
		String gender=request.getParameter("gender");
		String reupass=request.getParameter("reupass");
		
		if(uname==null||uname.trim().isEmpty()) {
			response.getWriter().append("请输入用户名");
		}else if(upass==null||upass.trim().isEmpty()) {
			response.getWriter().append("请输入密码");
		}else if(upass.equals(reupass)==false) {
			response.getWriter().append("两次输入的密码不一致");
		}else {
			udao.insert(uname, reupass, head, gender);
			response.getWriter().append("用户注册成功");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
