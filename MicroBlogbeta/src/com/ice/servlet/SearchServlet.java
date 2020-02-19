package com.ice.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ice.dao.SearchDao;
import com.ice.po.User;
import com.ice.po.Mood;

public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从前台获得数据
		String subsql = request.getParameter("subsql");
		String search = request.getParameter("textfield");
		//数据库操作
		if(subsql.equals("mb_content")){//不能用 == !!!
			SearchDao searchdao = new SearchDao();
			List<Mood> lstsearch1 = searchdao.ShowByContentDao(search);
			
	        //写入Session
			HttpSession session = request.getSession();
			session.setAttribute("lstsearch1", lstsearch1);
			//页面跳转
	        response.sendRedirect("../showsearchbycontent.jsp");
		}else{
			SearchDao searchdao = new SearchDao();
			List<User> lstsearch = searchdao.ShowByUserDao(search);
			
	        //写入Session
			HttpSession session = request.getSession();
			session.setAttribute("lstsearch", lstsearch);
			//页面跳转
	        response.sendRedirect("../showsearchbyuser.jsp");
		}
	}

}
