package com.ice.dao;

import java.sql.ResultSet;


import com.ice.dbutil.DBConn;
import com.ice.po.User;

public class FaceDao{

	//该函数执行的功能是添加头像到数据库
	public int saveFace(User face){
		//拆出对象，获得各个属性，以便于组织SQL语言
		String mf_imgSrc = face.getU_images();		
		int u_id=face.getU_id();
		System.out.println("uid "+u_id);
		int affect =0;
		String sql_insert_faces = "update user set u_images=? where u_id= ? ";
		//建立数据库链接
		DBConn dbConn = new DBConn();
		try {
			
			affect =dbConn.execOther(sql_insert_faces, new Object[]{mf_imgSrc,u_id});
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			dbConn.closeConn();
		}
		
		System.out.println("成功保存！");
		return affect;
	}
	
	
	//得到用户头像的url
    public String getFaceUrl(final int u_id)
    {
    	String getFaceUrl=null;
    	String strSQL="select * from user where u_id=?";
    	//建立数据库链接
    	DBConn dbconn =new DBConn();
    	//保存结果集
    	ResultSet rs=dbconn.execQuery(strSQL, new Object[]{u_id});
    	try {
    		while(rs.next()){      //这里必须循环遍历
    			getFaceUrl=rs.getString("u_images");//返回一条记录
    		}
			return getFaceUrl;
		} catch (Exception e) {
	
			e.printStackTrace();
			return null;
		}
		finally{
			//关闭数据库链接
			dbconn.closeConn();
		}
    }
}
