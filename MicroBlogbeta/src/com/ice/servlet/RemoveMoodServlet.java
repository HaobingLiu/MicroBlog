package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ice.dao.RemoveMoodDao;
//删除微博
public class RemoveMoodServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //获得要删除的微博的id
		int m_id = Integer.parseInt(request.getParameter("m_id"));
        //数据库操作
		RemoveMoodDao removeMoodDao = new RemoveMoodDao();
		removeMoodDao.removeDao(m_id);
         //页面跳转
		response.sendRedirect("../profile.jsp?No=");
	}

}