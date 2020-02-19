package com.ice.dao;

import com.ice.dbutil.DBConn;

public class RemoveMoodDao {
	//删除自己发表的微博
	public boolean removeDao(int m_id) {
		String strSQL = "delete from mood where m_id = ? ";
		//建立数据库连接
		DBConn dbConn = new DBConn();

		try {
			//执行数据库操作
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
