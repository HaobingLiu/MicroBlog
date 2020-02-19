package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//退出
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//注销Session
			HttpSession session = request.getSession();

			session.invalidate();//将session设置为失效,注销session 同时浏览器会立即创建一个新的session

			response.sendRedirect("../index.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
