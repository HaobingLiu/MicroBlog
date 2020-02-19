package com.ice.dao;
import com.ice.po.Comment;

import java.util.ArrayList;
import java.util.List;

import com.ice.dbutil.DBConn;

import java.sql.ResultSet;

public class getCommentDao {
	//�õ��û�����
	public List<Comment> getComment(int m_id)
	{
		List<Comment> lstComment=new ArrayList<Comment>();
		DBConn dbconn=new DBConn();
		String strSQL="select * from comment where m_id=?";
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{m_id});
		try {
			while(rs.next()){
				Comment comment=new Comment();
				
				int u_id=rs.getInt("u_id");
				comment.setU_id(u_id);			
				comment.setC_date(rs.getString("c_date"));
				comment.setC_content(rs.getString("c_content"));
				
				String u_name=showCommentName(u_id);
				comment.setU_name(u_name);//��ȡÿһ��΢�����û������൱�������������ˣ�ʵ��������
				
				FaceDao facedao=new FaceDao();
				String getFaceUrl=facedao.getFaceUrl(u_id);
				comment.setU_images(getFaceUrl);
				
				lstComment.add(comment);
			}
			return lstComment;	
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}finally{
			dbconn.closeConn();
		}
	}
	
	//�÷���������ҳ������ʾ��Ӧ��ÿһ��΢�����û���
    public String showCommentName(int u_id)
    {
    	String u_m_name=null;
    	String strSQL="select u_name from user where u_id= ? ";
    	//�������ݿ�����
    	DBConn dbconn=new DBConn();
    	//ִ�����ݿ��������������
    	ResultSet rs=dbconn.execQuery(strSQL, new Object[]{u_id});
    	try {
			//�����û���
			while(rs.next()){      //�������ѭ������
				u_m_name=rs.getString("u_name");//����һ����¼
			}
			return u_m_name;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally{
			dbconn.closeConn();
		}
    	
    }

}
