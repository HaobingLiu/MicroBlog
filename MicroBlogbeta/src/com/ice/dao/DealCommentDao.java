package com.ice.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ice.dbutil.DBConn;

//处理评论
public class DealCommentDao {

 public boolean dealComment(int m_id,int u_id,String c_content) {
         //获得一个日期对象并且赋值给c_date
    	 Date t_date_now=new Date();
         SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",java.util.Locale.CHINA);//
         String c_date=format.format(t_date_now);
         int flag =0;
       
    	 //建立数据库操作
    	 DBConn dbConn = new DBConn();
    	 String sql_Comment = "insert into comment values (null,?,?,?,?)";
         try {
        	 //执行数据库操作
        	flag = dbConn.execOther(sql_Comment, new Object[]{u_id,m_id,c_content,c_date});
			
		} catch (Exception e) {
			// TODO: handle exception
			//打印异常
			e.printStackTrace();
		}
    	 dbConn.closeConn();
    	 return flag>0?true:false;
     }
}
