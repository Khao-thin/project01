package com.bbs.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.userDao;

@WebServlet("/loginServlet.do")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userDao udao=new userDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
		String upass=request.getParameter("upass");
		Map<String,Object>user=udao.login(uname, upass);
		if(user!=null) {
			//登陆成功后将用户名保存到会话对象
			request.getSession().setAttribute("LoginedUser", user);
			System.out.println(user);
			response.getWriter().append("登陆成功");
		}else {
			response.getWriter().print("用户名或密码错误");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
