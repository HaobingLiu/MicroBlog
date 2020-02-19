package com.ice.dao;

import com.ice.dbutil.DBConn;
import com.ice.po.Mood;

public class PublishMoodDao {
  public boolean publishMood(Mood mood)
  {
	  //得到用户发表的微博的各个属性
	    int u_id=mood.getU_id();
		String m_date=mood.getM_date();
        String m_images=mood.getM_images();
		String m_content=mood.getM_content();
		//建立数据库连接
		DBConn dbconn=new DBConn();
		String strSQL="insert into mood values ( null,?,?,?,?)";
		//执行数据库操作
		int affectedRows=dbconn.execOther(strSQL, new Object[]{u_id,m_date,m_images,m_content});
	    boolean flag=(affectedRows>0)?true:false;
	    
	    dbconn.closeConn();
	    return flag;        
  }
}
