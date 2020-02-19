package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ice.dao.DealCommentDao;

public class DealCommentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//接收转发时传达过来的参数：m_id.u_id,c_content
		int u_id =Integer.parseInt( request.getParameter("u_id"));
		int m_id =Integer.parseInt( request.getParameter("m_id"));
		String c_content = request.getParameter("textfield");
		//调用转发的处理方法
		try {
			DealCommentDao  commentDao = new DealCommentDao();
			commentDao.dealComment(m_id, u_id, c_content);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//页面的跳转
		response.sendRedirect("../home.jsp?No=");
	}

}

