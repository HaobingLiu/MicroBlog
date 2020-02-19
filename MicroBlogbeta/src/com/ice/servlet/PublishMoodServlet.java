package com.ice.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ice.dao.PublishMoodDao;
import com.ice.po.Mood;
import com.ice.util.ApacheFormUtil;

public class PublishMoodServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		ApacheFormUtil formUtil=new ApacheFormUtil();
		Mood mood=(Mood)formUtil.getInstanceByUploadForm(request, this.getServletContext(), Mood.class, "upload/pic");
		PublishMoodDao publishdao=new PublishMoodDao();
		Date m_date_now=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",java.util.Locale.CHINA);
		String m_date=format.format(m_date_now);
		mood.setM_date(m_date);
		//执行数据库操作
		boolean flag=publishdao.publishMood(mood);
		int res=flag?3:4;
		//页面跳转
		response.sendRedirect("../profile.jsp?msg="+res+"&No=");

	}

}
