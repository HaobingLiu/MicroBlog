package com.ice.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ice.dbutil.DBConn;

//��������
public class DealCommentDao {

 public boolean dealComment(int m_id,int u_id,String c_content) {
         //���һ�����ڶ����Ҹ�ֵ��c_date
    	 Date t_date_now=new Date();
         SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",java.util.Locale.CHINA);//
         String c_date=format.format(t_date_now);
         int flag =0;
       
    	 //�������ݿ����
    	 DBConn dbConn = new DBConn();
    	 String sql_Comment = "insert into comment values (null,?,?,?,?)";
         try {
        	 //ִ�����ݿ����
        	flag = dbConn.execOther(sql_Comment, new Object[]{u_id,m_id,c_content,c_date});
			
		} catch (Exception e) {
			// TODO: handle exception
			//��ӡ�쳣
			e.printStackTrace();
		}
    	 dbConn.closeConn();
    	 return flag>0?true:false;
     }
}
