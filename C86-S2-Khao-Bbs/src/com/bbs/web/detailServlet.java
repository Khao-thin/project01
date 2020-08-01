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

import redis.clients.jedis.Jedis;

@WebServlet("/detailServlet.do")
public class detailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	topicDao tdao=new topicDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topicid=request.getParameter("topicid");
		List<Map<String, Object>>list=tdao.queryByDetail(topicid);
		
		/**
		 * 将访问的次数存入redis数据库
		 * redis很大工作是设计key值
		 * topic==>key topic:4  :  1,2,3
		 * 			   topic:5  :  数字
		 */
		
		Jedis jedis=new Jedis();
		//incr返回增长的值原值+1  实现自增
		long cnt=jedis.incr("topic:"+topicid);
		
		//将topic保存到zset中生成排行榜
		jedis.zadd("topic-zset",cnt,topicid);
		
		/**
		 * 返回的json数据格式
		 * [{原帖},{跟帖},{跟帖},{跟帖}...]
		 */
		//将浏览量添加到原帖对象
		list.get(0).put("cnt", cnt);
		
		jedis.close();
		
		Gson gson=new Gson();
		//将集合转成json格式的字符串
		String json=gson.toJson(list);
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
