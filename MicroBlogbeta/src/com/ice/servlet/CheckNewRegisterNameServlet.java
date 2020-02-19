package com.ice.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ice.dao.RegisterDao;

public class CheckNewRegisterNameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String u_name = request.getParameter("u_name");
		
		if ((new RegisterDao()).checkUser(u_name)) {
			out.print("true");
		} else {
			out.print("false");
		}
		
		out.flush();
		out.close();
		}
}
