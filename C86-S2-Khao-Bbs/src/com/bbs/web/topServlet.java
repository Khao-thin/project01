package com.bbs.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;


@WebServlet("/topServlet.do")
public class topServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Jedis jedis=new Jedis();
		Set<String>top=jedis.zrange("topic-zset", -10, -1);
		jedis.close();
		response.getWriter().append(new Gson().toJson(top));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
