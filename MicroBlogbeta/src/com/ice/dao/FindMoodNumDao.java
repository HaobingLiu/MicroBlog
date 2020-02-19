package com.ice.dao;

import java.sql.ResultSet;

import com.ice.dbutil.DBConn;

public class FindMoodNumDao {
	//�õ��ҵ�΢������
	public int getMyNum(int u_id)
	{
		int all=0;
		String strSQL="select count(*) from mood where u_id=?";
		DBConn dbconn=new DBConn();
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{u_id});
		try {
			while(rs.next()){      //�������ѭ������
				all=rs.getInt(1);//����һ����¼
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
	//�õ����ѵ�΢������
	public int getFriendsNum(int u_id)
	{
		int all=0;
		String strSQL="select count(*) from mood where u_id in (select f_user_id from friends where u_id = ?)";
		DBConn dbconn=new DBConn();
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{u_id});
		try {
			while(rs.next()){      //�������ѭ������
				all=rs.getInt(1);//����һ����¼
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
	//�õ��ܵ�΢������
	public int getAllNum(int u_id)
	{
		return (getMyNum(u_id)+getFriendsNum(u_id));
	}
		
}
