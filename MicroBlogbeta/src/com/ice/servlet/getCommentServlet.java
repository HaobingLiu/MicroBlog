package com.ice.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ice.dao.getCommentDao;
import com.ice.po.Comment;

public class getCommentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ǰ̨�������
		int m_id=Integer.parseInt(request.getParameter("m_id"));
		System.out.println("mid"+m_id);
		
		getCommentDao showcommentdao=new getCommentDao();
		List<Comment> lstComment=showcommentdao.getComment(m_id);
		//д��Session
		HttpSession session = request.getSession();
		session.setAttribute("lstComment",lstComment);
		//ҳ����ת
        response.sendRedirect("../showcomment.jsp");
		
	}
}