package com.ice.dao;

import java.sql.ResultSet;


import com.ice.dbutil.DBConn;
//添加好友
public class AddFriendsDao {
	public int addFriendsDao(int u_id,int f_user_id)
	{
		
	   int affectedRows=0;
	   //连接数据库
	   DBConn dbconn=new DBConn();
	   try {
		  //首先判断是不是已经是好友了
		   String isAlreadyFriend="select * from friends where u_id=? and f_user_id=?";
		   //执行查询并将查询结果返回给结果集
		   ResultSet rs=dbconn.execQuery(isAlreadyFriend,new Object[]{u_id,f_user_id});
		   if(rs.next()==false)
		   {
			   //非好友，添加为好友
			   String strSQL="insert into friends values(null,?,?)";
			   affectedRows=dbconn.execOther(strSQL, new Object[]{u_id,f_user_id});
		   }else
		   {
			   //已是好友，affectedRows为0
			   affectedRows=0;
		   } 
		   return affectedRows;
		
	   } catch (Exception e) {
		   //打印异常
		   e.printStackTrace();
		   return -1;
	   }finally{
		   dbconn.closeConn();
		
	   }
	   
	}

}
