package com.ice.dao;

import com.ice.dbutil.DBConn;

public class RemoveFriendsDao {

	public boolean removeDao(int u_id, int f_user_id) {
		// TODO Auto-generated method stub

		String strSQL = "delete from friends where u_id = ? and f_user_id= ?";
		//�������ݿ�����
		DBConn dbConn = new DBConn();

		try {
			//ִ�����ݿ����
			int affectedRows = dbConn.execOther(strSQL, new Object[] {u_id,f_user_id});
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
