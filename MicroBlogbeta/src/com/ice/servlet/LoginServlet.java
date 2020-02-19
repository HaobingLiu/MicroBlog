package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ice.dao.FaceDao;
import com.ice.dao.LoginDao;
import com.ice.po.User;

public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //��ǰ̨�������
		String u_name = request.getParameter("u_name").trim();
		String u_pwd = request.getParameter("u_pwd").trim();
		//ִ�����ݿ����
		LoginDao loginDao = new LoginDao();
		FaceDao facedao=new FaceDao();		
		int u_id = loginDao.checkUser(u_name, u_pwd);
		String u_nickname = loginDao.checkUserNickname(u_id);
		String u_position = loginDao.checkUserPosition(u_id);
        String u_images=facedao.getFaceUrl(u_id);
		boolean flag = (u_id > 0) ? true : false;
		if(flag){
			HttpSession session = request.getSession();
			//�½�һ��User����
			User user=new User();
			user.setU_id(u_id);
			user.setU_name(u_name);
			user.setU_images(u_images);
			//д��Session
			session.setAttribute("u_name", u_name);
			session.setAttribute("user",user);
			session.setAttribute("u_id", u_id);
			session.setAttribute("u_images", u_images);
			session.setAttribute("u_nickname", u_nickname);
			session.setAttribute("u_position", u_position);
			//д��Cookie
		
				Cookie usercookie = new Cookie("usercookie",u_name+"-"+u_pwd);  
				usercookie.setMaxAge(600);
				response.addCookie(usercookie);
			
		}
		//ҳ����ת
		String path = flag ? "../home.jsp?id="+u_id+"&No=" : "../error.jsp";
		response.sendRedirect(path);




	}
}
