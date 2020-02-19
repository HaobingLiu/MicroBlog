package com.ice.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ice.dbutil.DBConn;
import com.ice.po.Mood;


public class ShowMoodDao {
	public List<Mood> showAllMood(int user_id,int page,int pagesize)
	{
		//�½�һ��list�����û���װmood
		List<Mood> lstMood=new ArrayList<Mood>();
		//�������ݿ�����
		DBConn dbconn=new DBConn();
		//SQL ���
		String strSQL="select * from mood where u_id= ? union select * from mood where u_id in (select f_user_id from friends where u_id = ?) order by m_date desc limit " + (page-1)*pagesize + "," + pagesize+"";//SELECT * FROM table  LIMIT offset,rows  ��offset>=0ʱ�򣬱�ʾ��ȡ��ѯ���Ĵ�offset��ʼ��rows������
		//��������
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{user_id,user_id});
		try {
			//ѭ����mood��װ��lstMood
			while(rs.next()){
				Mood mood=new Mood();
				
				int m_id=rs.getInt("m_id");
				mood.setM_id(m_id);		
				
				mood.setM_date(rs.getString("m_date"));
				mood.setM_images(rs.getString("m_images"));
				mood.setM_content(rs.getString("m_content"));
				
				int u_id=rs.getInt("u_id");
				mood.setU_id(u_id);
				
				String u_m_name=showMoodName(u_id);
				mood.setU_m_name(u_m_name);//��ȡÿһ��΢�����û������൱�������������ˣ�ʵ��������
				
				FaceDao facedao=new FaceDao();
				String getFaceUrl=facedao.getFaceUrl(u_id);
				mood.setM_u_images(getFaceUrl);
				
				DealTransmitDao transmit=new DealTransmitDao();
			    int m_transmit_num=transmit.countTransmit(m_id);
				mood.setM_transmit_num(m_transmit_num);
				lstMood.add(mood);
			}
			//����lstMood
			return lstMood;				
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}finally{
			dbconn.closeConn();
		}
	}
	//��ʾ�ҵ�΢��
	public List<Mood> showMyMood(int user_id,int page,int pagesize)
	{
		//�½�һ��list����
		List<Mood> lstMood=new ArrayList<Mood>();
		//�������ݿ�����
		DBConn dbconn=new DBConn();
		String strSQL="select * from mood where u_id= ?  order by m_date desc limit "+(page-1)*pagesize+","+pagesize+"";
		//��������
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{user_id});
		try {
			//ѭ����mood��װ����lstMood
			while(rs.next())
			{
				Mood mood=new Mood();
				
				int m_id=rs.getInt("m_id");
				mood.setM_id(m_id);		
				
				mood.setM_date(rs.getString("m_date"));
				mood.setM_images(rs.getString("m_images"));
				mood.setM_content(rs.getString("m_content"));
				
				int u_id=rs.getInt("u_id");
				mood.setU_id(u_id);
				
				String u_m_name=showMoodName(u_id);
				mood.setU_m_name(u_m_name);//��ȡÿһ��΢�����û������൱�������������ˣ�ʵ��������
				
				FaceDao facedao=new FaceDao();
				String getFaceUrl=facedao.getFaceUrl(u_id);
				mood.setM_u_images(getFaceUrl);
				
				DealTransmitDao transmit=new DealTransmitDao();
			    int m_transmit_num=transmit.countTransmit(m_id);
				mood.setM_transmit_num(m_transmit_num);
				lstMood.add(mood);
			}
			return lstMood;	
		} catch (Exception e) {		
			e.printStackTrace();
			return null;
		}finally{
			dbconn.closeConn();
		}	
	}
	//��ʾ���ѵ�΢��
	public List<Mood> showFriendsMood(int user_id,int page,int pagesize)
	{
		//�½�һ��list����
		List<Mood> lstMood=new ArrayList<Mood>();
		//�������ݿ�����
		DBConn dbconn=new DBConn();
		String strSQL="select * from mood where u_id in (select f_user_id from friends where u_id = ?) order by m_date desc limit "+(page-1)*pagesize+","+pagesize+"";
		//��������
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{user_id});
		try {
			//ѭ����mood��װ��lstMood
			while(rs.next())
			{
				Mood mood=new Mood();
				
				int m_id=rs.getInt("m_id");
				mood.setM_id(m_id);		
				
				mood.setM_date(rs.getString("m_date"));
				mood.setM_images(rs.getString("m_images"));
				mood.setM_content(rs.getString("m_content"));
				
				int u_id=rs.getInt("u_id");
				mood.setU_id(u_id);
				System.out.println("ѡ���uid "+u_id);
				
				String u_m_name=showMoodName(u_id);
				mood.setU_m_name(u_m_name);//��ȡÿһ��΢�����û������൱�������������ˣ�ʵ��������
				
				FaceDao facedao=new FaceDao();
				String getFaceUrl=facedao.getFaceUrl(u_id);
				mood.setM_u_images(getFaceUrl);
				
				DealTransmitDao transmit=new DealTransmitDao();
			    int m_transmit_num=transmit.countTransmit(m_id);
				mood.setM_transmit_num(m_transmit_num);
				lstMood.add(mood);
			}
			return lstMood;		
		} catch (Exception e) {	
			e.printStackTrace();
			return null;
		}finally{
			dbconn.closeConn();
		}
		
	}
	//�÷���������ҳ������ʾ��Ӧ��ÿһ��΢�����û���
    public String showMoodName(int u_id)
    {
    	String u_m_name=null;
    	String strSQL="select u_name from user where u_id= ? ";
    	//�������ݿ�����
    	DBConn dbconn=new DBConn();
    	//ִ�����ݿ��������������
    	ResultSet rs=dbconn.execQuery(strSQL, new Object[]{u_id});
    	try {
			while(rs.next()){      //�������ѭ������
				u_m_name=rs.getString("u_name");//����һ����¼
			}
			return u_m_name;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally{
			dbconn.closeConn();
		}
    	
    }

}
