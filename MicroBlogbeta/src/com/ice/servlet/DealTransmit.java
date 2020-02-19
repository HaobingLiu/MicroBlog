package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ice.dao.DealTransmitDao;

public class DealTransmit extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//����ת��ʱ��������Ĳ�����m_id.u_id
		int u_id =Integer.parseInt( request.getParameter("u_id"));
		int m_id =Integer.parseInt( request.getParameter("m_id"));
	
		//����ת���Ĵ�����
		DealTransmitDao  transmitDao = new DealTransmitDao();
		boolean rows = transmitDao.dealTransmit(m_id, u_id);
		int flag = rows?1:2;
		String path="../home.jsp?msg="+flag+"&No=";
		response.sendRedirect(path);
	}

}
