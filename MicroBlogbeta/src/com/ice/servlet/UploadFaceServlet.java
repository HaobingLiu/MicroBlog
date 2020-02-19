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
/*��servlet��2������
 * 1���ϴ�ͷ��ͼƬ
 * 2�Ǵ����ݿ��õ��ոմ��ȥ��face,Ȼ��̬�滻
 * 
 */
public class UploadFaceServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("����ͼƬ�ϴ�!");
		ApacheFormUtil formUtil = new ApacheFormUtil();		
		User face = (User)formUtil.getInstanceByUploadForm(request, this.getServletContext(), User.class, "face");
		//����ͷ��
		new FaceDao().saveFace(face);				
		System.out.println("����ͼƬ����!");
		//���ͷ��·��
		HttpSession session = request.getSession();
		session.setAttribute("url", face.getU_images());
		String u_images= face.getU_images();
		session.setAttribute("u_images", u_images);
		response.sendRedirect("../myface.jsp");
		
		
	}

}
