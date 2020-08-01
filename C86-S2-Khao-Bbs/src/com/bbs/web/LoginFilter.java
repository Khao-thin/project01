package com.bbs.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


/**
 * Servlet ��ַ���ã�
 * 1���ļ�·��ƥ�䣺/hello.s
 * 2��Ŀ¼·��ƥ�䣺/page/ ƥ��/page/�µ�������Դ /page/1  /page/a  /page/adadas
 * 3���ļ���׺����   *.jsp  *.jpg  *.do  *.s
 * 
 * Ŀ¼����·��+�ļ�����׺ �ǷǷ�  /page/*.jsp  ��
 * 
 * @author 23163
 *
 */
@WebFilter(urlPatterns={"*.jsp","*.do","*.s"})
public class LoginFilter implements Filter {
	
	//���ٷ���
	public void destroy() {
		System.out.println("====loginfilter======destroy====");
	}

	/**
	 * ִ�й���
	 * 
	 * servlet
	 * HttpServletRequest req,HttpServletResponse resp
	 * 
	 * ServletRequest request,ServletResponse response
	 * 
	 * FilterChain chain ��������
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//�������������ַ���==>post
		request.setCharacterEncoding("UTF-8");
		//������Ӧ������ַ���
		response.setCharacterEncoding("UTF-8");
		//������ҳ���ַ���
		response.setContentType("text/html;charset=utf-8");
		
		
		//HttpSession session=((HttpServletRequest)request).getSession();
		//System.out.println("-----------"+session.getId());
		//��ȡ����ֵ
		/*
		 * String user=(String)session.getAttribute("loginedUser"); if(user==null) {
		 * response.getWriter().append("please login");
		 * //�ж�ִ�У���ִ��chain.doFilter(request,response); return; }else {
		 * response.getWriter().append("loginedUser:"+user); }
		 */
		//ִ�й��������е���һ����������doFilter������Ѿ������һ����������
		//��ô��ʾ�������Ŀ����Դ(jsp,servlet,html,js...)
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
