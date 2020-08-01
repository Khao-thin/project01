package com.bbs.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.topicDao;
import com.google.gson.Gson;

@WebServlet("/list.do")
public class listServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	topicDao tdao=new topicDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardid=request.getParameter("boardid");
		List<Map<String,Object>>list=tdao.queryByBoard(boardid);
		Gson gson=new Gson();
		//将集合转成json格式的字符串
		String json=gson.toJson(list);
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
