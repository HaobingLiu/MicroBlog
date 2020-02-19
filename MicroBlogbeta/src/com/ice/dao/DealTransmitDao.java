package com.ice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ice.dbutil.DBConn;

//������
public class DealTransmitDao {

     public boolean dealTransmit(int m_id,int u_id) {
    	 
    	 //m_id��u_id��ͨ������ҳ�洫�ݹ����ģ�֮���t_dateͨ�����µõ�
    	 Date t_date_now=new Date();
         SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",java.util.Locale.CHINA);
         String t_date=format.format(t_date_now);
    	 //�������ݿ����
    	 DBConn dbConn = new DBConn();
    	 String sql_Transmit = "insert into transmit values (null,?,?,?)";
    	 int flag = dbConn.execOther(sql_Transmit, new Object[]{m_id,u_id,t_date});
    	 dbConn.closeConn();
    	 return flag>0?true:false;
     }
     public int countTransmit(int m_id){
    	 //�������ݿ��ѯ����
    	 DBConn dbConn = new DBConn();
    	 int count = 0;
    	 String strSQL = "select count(*) from transmit where m_id=?";
    	 ResultSet rs = dbConn.execQuery(strSQL, new Object[]{m_id});
    	 try {
		        while(rs.next()){      //�������ѭ������
		        	count = rs.getInt(1);//����һ����¼
		        }
				return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			dbConn.closeConn();
		}
     }
}