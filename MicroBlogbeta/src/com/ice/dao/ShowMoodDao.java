package com.ice.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ice.dbutil.DBConn;
import com.ice.po.Mood;


public class ShowMoodDao {
	public List<Mood> showAllMood(int user_id,int page,int pagesize)
	{
		//新建一个list对象用户封装mood
		List<Mood> lstMood=new ArrayList<Mood>();
		//建立数据库连接
		DBConn dbconn=new DBConn();
		//SQL 语句
		String strSQL="select * from mood where u_id= ? union select * from mood where u_id in (select f_user_id from friends where u_id = ?) order by m_date desc limit " + (page-1)*pagesize + "," + pagesize+"";//SELECT * FROM table  LIMIT offset,rows  当offset>=0时候，表示提取查询到的从offset开始的rows条数据
		//保存结果集
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{user_id,user_id});
		try {
			//循环将mood封装进lstMood
			while(rs.next()){
				Mood mood=new Mood();
				
				int m_id=rs.getInt("m_id");
				mood.setM_id(m_id);		
				
				mood.setM_date(rs.getString("m_date"));
				mood.setM_images(rs.getString("m_images"));
				mood.setM_content(rs.getString("m_content"));
				
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
				lstMood.add(mood);
			}
			//返回lstMood
			return lstMood;				
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}finally{
			dbconn.closeConn();
		}
	}
	//显示我的微博
	public List<Mood> showMyMood(int user_id,int page,int pagesize)
	{
		//新建一个list对象
		List<Mood> lstMood=new ArrayList<Mood>();
		//建立数据库连接
		DBConn dbconn=new DBConn();
		String strSQL="select * from mood where u_id= ?  order by m_date desc limit "+(page-1)*pagesize+","+pagesize+"";
		//保存结果集
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{user_id});
		try {
			//循环将mood封装进入lstMood
			while(rs.next())
			{
				Mood mood=new Mood();
				
				int m_id=rs.getInt("m_id");
				mood.setM_id(m_id);		
				
				mood.setM_date(rs.getString("m_date"));
				mood.setM_images(rs.getString("m_images"));
				mood.setM_content(rs.getString("m_content"));
				
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
				lstMood.add(mood);
			}
			return lstMood;	
		} catch (Exception e) {		
			e.printStackTrace();
			return null;
		}finally{
			dbconn.closeConn();
		}	
	}
	//显示好友的微博
	public List<Mood> showFriendsMood(int user_id,int page,int pagesize)
	{
		//新建一个list对象
		List<Mood> lstMood=new ArrayList<Mood>();
		//建立数据库连接
		DBConn dbconn=new DBConn();
		String strSQL="select * from mood where u_id in (select f_user_id from friends where u_id = ?) order by m_date desc limit "+(page-1)*pagesize+","+pagesize+"";
		//保存结果集
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{user_id});
		try {
			//循环将mood封装进lstMood
			while(rs.next())
			{
				Mood mood=new Mood();
				
				int m_id=rs.getInt("m_id");
				mood.setM_id(m_id);		
				
				mood.setM_date(rs.getString("m_date"));
				mood.setM_images(rs.getString("m_images"));
				mood.setM_content(rs.getString("m_content"));
				
				int u_id=rs.getInt("u_id");
				mood.setU_id(u_id);
				System.out.println("选择的uid "+u_id);
				
				String u_m_name=showMoodName(u_id);
				mood.setU_m_name(u_m_name);//获取每一条微博的用户名，相当于两个表连接了，实现在下面
				
				FaceDao facedao=new FaceDao();
				String getFaceUrl=facedao.getFaceUrl(u_id);
				mood.setM_u_images(getFaceUrl);
				
				DealTransmitDao transmit=new DealTransmitDao();
			    int m_transmit_num=transmit.countTransmit(m_id);
				mood.setM_transmit_num(m_transmit_num);
				lstMood.add(mood);
			}
			return lstMood;		
		} catch (Exception e) {	
			e.printStackTrace();
			return null;
		}finally{
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
