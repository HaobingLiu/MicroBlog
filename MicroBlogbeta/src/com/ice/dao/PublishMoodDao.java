package com.ice.dao;

import com.ice.dbutil.DBConn;
import com.ice.po.Mood;

public class PublishMoodDao {
  public boolean publishMood(Mood mood)
  {
	  //�õ��û������΢���ĸ�������
	    int u_id=mood.getU_id();
		String m_date=mood.getM_date();
        String m_images=mood.getM_images();
		String m_content=mood.getM_content();
		//�������ݿ�����
		DBConn dbconn=new DBConn();
		String strSQL="insert into mood values ( null,?,?,?,?)";
		//ִ�����ݿ����
		int affectedRows=dbconn.execOther(strSQL, new Object[]{u_id,m_date,m_images,m_content});
	    boolean flag=(affectedRows>0)?true:false;
	    
	    dbconn.closeConn();
	    return flag;        
  }
}
