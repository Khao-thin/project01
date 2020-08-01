package com.bbs.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

@WebServlet("/addzanServlet.do")
public class addzanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 设计Redis key 值
		 * 		key		:	topic-zan:帖子id
		 * 		value	:	set集合，记录每一个点赞的用户的id
		 * 	
		 * 	用户id	:	直接从会话中获取loginedUser
		 * 	帖子id	:	从请求中获取
		 */
		@SuppressWarnings("unchecked")
		Map<String,Object>user=(Map<String, Object>) request.getSession().getAttribute("LoginedUser");
		Object uid=user.get("uid");
		String topicid=request.getParameter("topicid");
		Jedis jedis=new Jedis();
		jedis.sadd("topic-zan:"+topicid,uid.toString());
		long cnt=jedis.scard("topic-zan:"+topicid);
		jedis.close();
		response.getWriter().append("{\"cnt\":"+cnt+"}");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
