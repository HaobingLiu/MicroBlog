package com.ice.dao;
import com.ice.po.Comment;

import java.util.ArrayList;
import java.util.List;

import com.ice.dbutil.DBConn;

import java.sql.ResultSet;

public class getCommentDao {
	//得到用户评论
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
				comment.setU_name(u_name);//获取每一条微博的用户名，相当于两个表连接了，实现在下面
				
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
	
	//该方法用于在页面中显示对应于每一条微博的用户名
    public String showCommentName(int u_id)
    {
    	String u_m_name=null;
    	String strSQL="select u_name from user where u_id= ? ";
    	//建立数据库连接
    	DBConn dbconn=new DBConn();
    	//执行数据库操作并保存结果集
    	ResultSet rs=dbconn.execQuery(strSQL, new Object[]{u_id});
    	try {
			//返回用户名
			while(rs.next()){      //这里必须循环遍历
				u_m_name=rs.getString("u_name");//返回一条记录
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
