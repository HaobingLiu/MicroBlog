package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ice.dao.UpdatePwdDao;
//修改密码
public class UpdatePwdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从前台获得数据
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		
		String nu_pwd = request.getParameter("nu_pwd").trim();
	    //数据库操作
		UpdatePwdDao pwdDao = new UpdatePwdDao();
		pwdDao.updatePwd(u_id, nu_pwd);
		//页面跳转
		response.sendRedirect("../home.jsp?No=");
			
	}
}
