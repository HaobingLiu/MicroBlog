package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ice.dao.RemoveFriendsDao;

public class RemoveFriendsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		int f_user_id = Integer.parseInt(request.getParameter("f_user_id"));	
        //数据库操作
		RemoveFriendsDao removeFriendsDao = new RemoveFriendsDao();
		removeFriendsDao.removeDao(u_id,f_user_id);
         //页面跳转
		response.sendRedirect("../friend.jsp?No=");
	}

}