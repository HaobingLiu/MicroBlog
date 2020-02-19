package com.ice.dao;

import java.sql.ResultSet;


import com.ice.dbutil.DBConn;
import com.ice.po.User;

public class FaceDao{

	//�ú���ִ�еĹ��������ͷ�����ݿ�
	public int saveFace(User face){
		//������󣬻�ø������ԣ��Ա�����֯SQL����
		String mf_imgSrc = face.getU_images();		
		int u_id=face.getU_id();
		System.out.println("uid "+u_id);
		int affect =0;
		String sql_insert_faces = "update user set u_images=? where u_id= ? ";
		//�������ݿ�����
		DBConn dbConn = new DBConn();
		try {
			
			affect =dbConn.execOther(sql_insert_faces, new Object[]{mf_imgSrc,u_id});
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			//�ر����ݿ�����
			dbConn.closeConn();
		}
		
		System.out.println("�ɹ����棡");
		return affect;
	}
	
	
	//�õ��û�ͷ���url
    public String getFaceUrl(final int u_id)
    {
    	String getFaceUrl=null;
    	String strSQL="select * from user where u_id=?";
    	//�������ݿ�����
    	DBConn dbconn =new DBConn();
    	//��������
    	ResultSet rs=dbconn.execQuery(strSQL, new Object[]{u_id});
    	try {
    		while(rs.next()){      //�������ѭ������
    			getFaceUrl=rs.getString("u_images");//����һ����¼
    		}
			return getFaceUrl;
		} catch (Exception e) {
	
			e.printStackTrace();
			return null;
		}
		finally{
			//�ر����ݿ�����
			dbconn.closeConn();
		}
    }
}
