package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ice.dao.RegisterDao;
import com.ice.po.User;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         //���Ҫ����û��ĸ�������
		String u_name = request.getParameter("u_email").trim();
		String u_pwd = request.getParameter("u_pwd").trim();
		String u_rpwd = request.getParameter("u_rpwd").trim();
		String u_email = request.getParameter("u_email").trim();
		String u_nickname = request.getParameter("u_nickname").trim();
		String u_sex = request.getParameter("u_sex").trim();
		String u_position = request.getParameter("u_position").trim();
		boolean flag=false;
		RegisterDao tstuname = new RegisterDao();
		boolean tst=tstuname.checkUser(u_name);
		if(u_pwd.equals(u_rpwd)&&!tst){
	        //���������Է�װ��User������
			User user = new User();
			user.setU_name(u_name);
			user.setU_pwd(u_pwd);
			user.setU_rpwd(u_rpwd);
			user.setU_email(u_email);
			user.setU_nickname(u_nickname);
			user.setU_sex(u_sex);
			user.setU_position(u_position);
			//�½�һ��RegisterDao
			RegisterDao registerDao = new RegisterDao();
	        //���ע������ݿ����
			flag = registerDao.registerUser(user);
	        //��һЩ���ݱ��浽session��
			HttpSession session = request.getSession();
			session.setAttribute("u_nickname", u_nickname);
			session.setAttribute("u_position", u_position);
		}
		//ҳ����ת
		String path = flag ? "../index.jsp" : "../error.jsp";
		response.sendRedirect(path);

	}

}
