package com.ice.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConn {
	//�����ԡ��ķ���
	
	//������Ľӿ�
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//�ĸ�����
	//method1: �������ݿ������
	private void getConntion(){		
		try {
			//1: ��������������Java����ԭ��
			Class.forName(Config.CLASS_NAME);
			//2:����Connection�ӿڶ������ڻ�ȡMySQL���ݿ�����Ӷ�������������url�����ַ���    �˺�  ����
			String url = Config.DATABASE_URL+"://"+Config.SERVER_IP+":"+Config.SERVER_PORT+"/"+Config.DATABASE_SID;
			conn = DriverManager.getConnection(url,Config.USERNAME,Config.PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	//method2���ر����ݿ�ķ���
	public void closeConn(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

    
	//method3: ר�����ڷ��� ��ɾ�� ���ķ���
	//ִ�в������ݿ�Ĺ����ǣ�ͨ��Connection���Ӷ����ȡStatement������ͨ��Statement����ִ�в���
	public int execOther(final String strSQL,final Object[] params ){
		//1�����÷���1����ȡ���ݿ�����
		getConntion();
		//2��Ԥ�ȴ�ӡ������ִ�е�SQL��䣬������Ŀ����
		System.out.println("SQL:> "+strSQL);
		try {
			//3������Statement�ӿڶ���
			pstmt = conn.prepareStatement(strSQL);
			//4����̬Ϊpstmt����ֵ
			for(int i=0; i< params.length ;i++){
				pstmt.setObject(i+1, params[i]);
			}
			//5��ʹ��Statement������SQL���
			int affectedRows = pstmt.executeUpdate();
			//6�����ؽ��
			return affectedRows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}


	//method4: ר�����ڷ��Ͳ�ѯ���
	public ResultSet execQuery(final String strSQL,final Object[] params){
		//1����ȡ���ݿ�����
		getConntion();
		//2��Ԥ�ȴ�ӡ������ִ�е�SQL��䣬������Ŀ����
		System.out.println("SQL:> "+strSQL);
		try {
			//3������PreparedStatement�ӿڶ���
			pstmt = conn.prepareStatement(strSQL);
			//4����̬Ϊpstmt����ֵ
			for(int i=0; i< params.length ;i++){
				pstmt.setObject(i+1, params[i]);
			}		
			//5��ʹ��Statement������SQL���
			rs = pstmt.executeQuery();
			//6�����ؽ��
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
