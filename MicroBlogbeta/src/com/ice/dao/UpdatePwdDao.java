package com.ice.dao;


import com.ice.dbutil.DBConn;

public class UpdatePwdDao {
	
	public boolean updatePwd(final int u_id, final String u_pwd) {
		String strSQL = "update user set u_pwd=? where u_id = ?";
		DBConn dbConn = new DBConn();
		try {

			int affectRows = dbConn.execOther(strSQL, new Object[] { u_pwd,
					u_id });

			return affectRows > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			dbConn.closeConn();
		}

	}

}
