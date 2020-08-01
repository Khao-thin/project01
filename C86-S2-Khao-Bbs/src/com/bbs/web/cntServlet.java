package com.bbs.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

@WebServlet("/cntServlet.do")
public class cntServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 获取帖子的点赞数量
		 */
		Jedis jedis=new Jedis();
		String topicid=request.getParameter("topicid");
		long cnt=jedis.scard("topic-zan:"+topicid);
		jedis.close();
		response.getWriter().append("{\"cnt\":"+cnt+"}");//json对象
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
