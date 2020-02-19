package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//�˳�
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//ע��Session
			HttpSession session = request.getSession();

			session.invalidate();//��session����ΪʧЧ,ע��session ͬʱ���������������һ���µ�session

			response.sendRedirect("../index.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
