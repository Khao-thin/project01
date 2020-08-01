package com.bbs.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.boardDao;
import com.google.gson.Gson;

@WebServlet("/indexServlet.do")
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boardDao bdao=new boardDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>>list=bdao.queryForIndex();
		Gson gson=new Gson();
		//将集合转成json格式的字符串
		String json=gson.toJson(list);
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
