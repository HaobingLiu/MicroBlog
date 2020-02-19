package com.ice.dao;

import java.sql.ResultSet;

import com.ice.dbutil.DBConn;

public class FindMoodNumDao {
	//得到我的微博数量
	public int getMyNum(int u_id)
	{
		int all=0;
		String strSQL="select count(*) from mood where u_id=?";
		DBConn dbconn=new DBConn();
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{u_id});
		try {
			while(rs.next()){      //这里必须循环遍历
				all=rs.getInt(1);//返回一条记录
			}	
			return all;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			dbconn.closeConn();
		}
	}
	//得到好友的微博数量
	public int getFriendsNum(int u_id)
	{
		int all=0;
		String strSQL="select count(*) from mood where u_id in (select f_user_id from friends where u_id = ?)";
		DBConn dbconn=new DBConn();
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{u_id});
		try {
			while(rs.next()){      //这里必须循环遍历
				all=rs.getInt(1);//返回一条记录
			}	
			return all;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			dbconn.closeConn();
		}
	}
	//得到总的微博数量
	public int getAllNum(int u_id)
	{
		return (getMyNum(u_id)+getFriendsNum(u_id));
	}
		
}
