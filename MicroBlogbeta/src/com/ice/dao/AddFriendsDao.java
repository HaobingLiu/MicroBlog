package com.ice.dao;

import java.sql.ResultSet;


import com.ice.dbutil.DBConn;
//��Ӻ���
public class AddFriendsDao {
	public int addFriendsDao(int u_id,int f_user_id)
	{
		
	   int affectedRows=0;
	   //�������ݿ�
	   DBConn dbconn=new DBConn();
	   try {
		  //�����ж��ǲ����Ѿ��Ǻ�����
		   String isAlreadyFriend="select * from friends where u_id=? and f_user_id=?";
		   //ִ�в�ѯ������ѯ������ظ������
		   ResultSet rs=dbconn.execQuery(isAlreadyFriend,new Object[]{u_id,f_user_id});
		   if(rs.next()==false)
		   {
			   //�Ǻ��ѣ����Ϊ����
			   String strSQL="insert into friends values(null,?,?)";
			   affectedRows=dbconn.execOther(strSQL, new Object[]{u_id,f_user_id});
		   }else
		   {
			   //���Ǻ��ѣ�affectedRowsΪ0
			   affectedRows=0;
		   } 
		   return affectedRows;
		
	   } catch (Exception e) {
		   //��ӡ�쳣
		   e.printStackTrace();
		   return -1;
	   }finally{
		   dbconn.closeConn();
		
	   }
	   
	}

}
