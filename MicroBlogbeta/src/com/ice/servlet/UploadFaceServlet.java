package com.ice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ice.dao.FaceDao;
import com.ice.po.User;
import com.ice.util.ApacheFormUtil;
/*该servlet的2个作用
 * 1是上传头像图片
 * 2是从数据库拿到刚刚存进去的face,然后动态替换
 * 
 */
public class UploadFaceServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("进入图片上传!");
		ApacheFormUtil formUtil = new ApacheFormUtil();		
		User face = (User)formUtil.getInstanceByUploadForm(request, this.getServletContext(), User.class, "face");
		//保存头像
		new FaceDao().saveFace(face);				
		System.out.println("进入图片更新!");
		//查出头像路径
		HttpSession session = request.getSession();
		session.setAttribute("url", face.getU_images());
		String u_images= face.getU_images();
		session.setAttribute("u_images", u_images);
		response.sendRedirect("../myface.jsp");
		
		
	}

}
