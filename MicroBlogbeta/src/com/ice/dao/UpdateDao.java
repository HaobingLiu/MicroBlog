package com.ice.dao;

import com.ice.dbutil.DBConn;
import com.ice.po.User;

public class UpdateDao {
//�����û�����
	public boolean updateUser(final User user) {
		//�õ����µ��û������еĸ�����Ϣ
		int u_id = user.getU_id();
		String u_nickname = user.getU_nickname();
		String u_sex = user.getU_sex();
		String u_birth = user.getU_birth();
		String u_position = user.getU_position();
		String u_qq = user.getU_qq();
		String u_sign = user.getU_sign();
       //ִ�����ݿ����
		String strSQL = "update user set u_nickname=?,u_position=?,u_sex=?,u_birth=?,u_qq=?,u_sign=? where u_id = ?";
		DBConn dbconn = new DBConn();

		int affectRows = dbconn.execOther(strSQL, new Object[] { u_nickname,u_position, u_sex, u_birth, u_qq, u_sign,u_id });
		
		dbconn.closeConn();

		return affectRows > 0 ? true : false;

	}

}
