package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ice.dao.UpdateDao;
import com.ice.po.User;
//更新用户资料
public class UpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         //从前台获得数据
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		String u_nickname = request.getParameter("u_nickname");
		String u_sex = request.getParameter("u_sex");
		String u_birth = request.getParameter("u_birth");
		String u_position = request.getParameter("u_position");
		String u_qq = request.getParameter("u_qq");
		String u_sign = request.getParameter("u_sign");
		//新建一个User对象封装各个属性
		User user = new User();
		user.setU_id(u_id);	
		user.setU_nickname(u_nickname);
		user.setU_sex(u_sex);
		user.setU_birth(u_birth);
		user.setU_position(u_position);
		user.setU_qq(u_qq);
		user.setU_sign(u_sign);
       //数据库操作
		UpdateDao updateDao = new UpdateDao();
		boolean flag = updateDao.updateUser(user);
		//将一些信息写入Session中
		HttpSession session = request.getSession();
		session.setAttribute("u_nickname", u_nickname);
		session.setAttribute("u_sign", u_sign);
		int res = flag ? 5: 6;
		//执行页面跳转
		response.sendRedirect("../home.jsp?msg=" + res+"&No=");
	}

}
