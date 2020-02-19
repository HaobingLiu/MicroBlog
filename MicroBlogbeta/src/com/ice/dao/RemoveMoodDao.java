package com.ice.dao;

import com.ice.dbutil.DBConn;

public class RemoveMoodDao {
	//ɾ���Լ������΢��
	public boolean removeDao(int m_id) {
		String strSQL = "delete from mood where m_id = ? ";
		//�������ݿ�����
		DBConn dbConn = new DBConn();

		try {
			//ִ�����ݿ����
			int affectedRows = dbConn.execOther(strSQL, new Object[] { m_id });
			boolean flag = (affectedRows > 0) ? true : false;
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			dbConn.closeConn();
		}
	}

}
