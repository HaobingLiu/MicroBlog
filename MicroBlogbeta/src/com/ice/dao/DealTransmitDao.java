package com.ice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ice.dbutil.DBConn;

//处理赞
public class DealTransmitDao {

     public boolean dealTransmit(int m_id,int u_id) {
    	 
    	 //m_id和u_id是通过接收页面传递过来的，之后的t_date通过如下得到
    	 Date t_date_now=new Date();
         SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",java.util.Locale.CHINA);
         String t_date=format.format(t_date_now);
    	 //建立数据库操作
    	 DBConn dbConn = new DBConn();
    	 String sql_Transmit = "insert into transmit values (null,?,?,?)";
    	 int flag = dbConn.execOther(sql_Transmit, new Object[]{m_id,u_id,t_date});
    	 dbConn.closeConn();
    	 return flag>0?true:false;
     }
     public int countTransmit(int m_id){
    	 //建立数据库查询操作
    	 DBConn dbConn = new DBConn();
    	 int count = 0;
    	 String strSQL = "select count(*) from transmit where m_id=?";
    	 ResultSet rs = dbConn.execQuery(strSQL, new Object[]{m_id});
    	 try {
		        while(rs.next()){      //这里必须循环遍历
		        	count = rs.getInt(1);//返回一条记录
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