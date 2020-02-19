package com.ice.dao;

import java.util.ArrayList;
import java.util.List;

import com.ice.dbutil.DBConn;
import com.ice.po.User;
import com.ice.po.Mood;

import java.sql.ResultSet;

public class SearchDao {
	//执行查询操作
	public List<Mood> ShowByContentDao(String searc)
	{
		//建立一个list
		List<Mood> lstMoodf=new ArrayList<Mood>();
		//模糊查询语句
		String strSQL="select * from mood where m_content like '%"+searc+"%'";
		DBConn dbconn=new DBConn();
		//保存结果集
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{});
		try {		
			//循环将结果添加到lstMoodf中
			while(rs.next())
			{   
				Mood mood=new Mood();
				
				int m_id=rs.getInt("m_id");
				mood.setM_id(m_id);		
				
				mood.setM_date(rs.getString("m_date"));
				mood.setM_images(rs.getString("m_images"));
				mood.setM_content(rs.getString("m_content"));
				mood.setM_comment_num(rs.getInt("m_comment_num"));
				
				int u_id=rs.getInt("u_id");
				mood.setU_id(u_id);
				
				String u_m_name=showMoodName(u_id);
				mood.setU_m_name(u_m_name);//获取每一条微博的用户名，相当于两个表连接了，实现在下面
				
				FaceDao facedao=new FaceDao();
				String getFaceUrl=facedao.getFaceUrl(u_id);
				mood.setM_u_images(getFaceUrl);
				
				DealTransmitDao transmit=new DealTransmitDao();
			    int m_transmit_num=transmit.countTransmit(m_id);
				mood.setM_transmit_num(m_transmit_num);
				lstMoodf.add(mood);		
			}
			return lstMoodf;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			dbconn.closeConn();
		}
	}
	
	public List<User> ShowByUserDao(String searc)
	{
		//建立一个list
		List<User> lstuser=new ArrayList<User>();
		//模糊查询语句
		String strSQL="select * from user where u_name like '%"+searc+"%'";
		DBConn dbconn=new DBConn();
		//保存结果集
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{});
		try {		
			//循环将结果添加到lstuser中
			while(rs.next())
			{   
				int u_id=rs.getInt("u_id");
				User user=new User();
				user.setU_id(u_id);
				FaceDao facedao=new FaceDao();
				String face=facedao.getFaceUrl(u_id);
				user.setU_images(face);
				user.setU_name(rs.getString("u_name"));
				user.setU_position(rs.getString("u_position"));
				user.setU_birth(rs.getString("u_birth"));
				user.setU_sign(rs.getString("u_sign"));
				lstuser.add(user);		
			}
			return lstuser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			dbconn.closeConn();
		}
	}
	
	//该方法用于在页面中显示对应于每一条微博的用户名
    public String showMoodName(int u_id)
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
