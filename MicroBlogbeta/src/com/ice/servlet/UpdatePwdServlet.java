package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ice.dao.UpdatePwdDao;
//�޸�����
public class UpdatePwdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ǰ̨�������
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		
		String nu_pwd = request.getParameter("nu_pwd").trim();
	    //���ݿ����
		UpdatePwdDao pwdDao = new UpdatePwdDao();
		pwdDao.updatePwd(u_id, nu_pwd);
		//ҳ����ת
		response.sendRedirect("../home.jsp?No=");
			
	}
}
