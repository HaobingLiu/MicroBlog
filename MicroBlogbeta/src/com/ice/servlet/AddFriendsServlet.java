package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ice.dao.AddFriendsDao;

public class AddFriendsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得要添加好友的各个属性，如u_id、f_user_id
		int u_id=Integer.parseInt(request.getParameter("u_id"));
		System.out.println("想关注的为u_id "+u_id);
		int f_user_id=Integer.parseInt(request.getParameter("f_user_id"));
		//新建一个添加好友的AddFriendsDao
        AddFriendsDao addfriendsdao=new AddFriendsDao();
        //执行数据库操作
        int flag=addfriendsdao.addFriendsDao(u_id, f_user_id);
        int res=flag>0 ? 7:8;
        //页面跳转
	    response.sendRedirect("../friend.jsp?msg="+res+"&No=");
	}



}
