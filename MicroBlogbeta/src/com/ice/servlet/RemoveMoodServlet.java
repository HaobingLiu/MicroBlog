package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ice.dao.RemoveMoodDao;
//ɾ��΢��
public class RemoveMoodServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //���Ҫɾ����΢����id
		int m_id = Integer.parseInt(request.getParameter("m_id"));
        //���ݿ����
		RemoveMoodDao removeMoodDao = new RemoveMoodDao();
		removeMoodDao.removeDao(m_id);
         //ҳ����ת
		response.sendRedirect("../profile.jsp?No=");
	}

}