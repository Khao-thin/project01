package com.bbs.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.topicDao;

/**
 * Servlet implementation class addtopicServlet
 */
@WebServlet("/addtopicServlet.do")
public class addtopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	topicDao tdao=new topicDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String,Object>)request.getSession().getAttribute("LoginedUser");
		
		if(user==null) {
			response.getWriter().append("请先登录系统");
			return;
		}
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String boardid=request.getParameter("boardid");
		
		String uid=""+user.get("uid");
		System.out.println(uid);
		if(title==null||title.trim().isEmpty()) {
			response.getWriter().append("请填写帖子标题");
		}else if(content==null||content.trim().isEmpty()) {
			response.getWriter().append("请填写帖子内容");
		}else {
			tdao.insert(title, content, uid, boardid);
			response.getWriter().append("发表帖子成功");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
