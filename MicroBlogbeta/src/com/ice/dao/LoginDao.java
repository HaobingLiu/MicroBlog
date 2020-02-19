package com.ice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ice.dbutil.DBConn;

public class LoginDao {
    //��½��֤
	public int checkUser(final String u_name, final String u_pwd) {
		int res=0;
		String strSQL = "select * from user where u_name = ? and u_pwd = ?";
		//�������ݿ�����
		DBConn dbConn = new DBConn();
		//��������
		ResultSet rs = dbConn.execQuery(strSQL, new Object[] { u_name, u_pwd });
		try {
			//���rest���α�λ�ڵ�һ��֮ǰ����˵�һ�ε���next()�������α����ڵ�һ���ϣ�ʹ����Ϊ��ǰ�С�����ÿ�ε��� next()�������α������ƶ�һ�У����մ������µĴ����ȡ ResultSet�С�
			//�����½�ɹ��������û�id
			while(rs.next()){      //�������ѭ������
				res=rs.getInt("u_id");//����һ����¼
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			//��½ʧ�ܷ���-1
			return -1;
		} finally {
			dbConn.closeConn();
		}
	}
	//�õ��û��ǳ�
	public String checkUserNickname(final int u_id) {
		String res="";
		String strSQL = "select * from user where u_id = ?";
		//�������ݿ�����
		DBConn dbConn = new DBConn();
		//��������
		ResultSet rs = dbConn.execQuery(strSQL, new Object[] { u_id });
		try {
           //�����û��ǳ�
			while(rs.next()){      //�������ѭ������
				res=rs.getString("u_nickname");//����һ����¼
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			//���δ�ܳɹ���ã��򷵻�null
			return null;
		} finally {
			//�ر����ݿ�����
			dbConn.closeConn();
		}
	}
//�õ��û������ڵ�
	public String checkUserPosition(final int u_id) {
		String res="";
		String strSQL = "select * from user where u_id = ?";
		//�������ݿ�����
		DBConn dbConn = new DBConn();
		//��������
		ResultSet rs = dbConn.execQuery(strSQL, new Object[] { u_id });
		try {
			//�����û����ڵ�
			while(rs.next()){      //�������ѭ������
				res=rs.getString("u_position");//����һ����¼
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbConn.closeConn();
		}

	}

}
