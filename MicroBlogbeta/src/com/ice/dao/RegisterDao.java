package com.ice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ice.dbutil.DBConn;
import com.ice.po.User;

public class RegisterDao {

	public boolean registerUser(final User user) {
		//获得要注册用户的各个属性
		String u_email = user.getU_email();
		String u_pwd = user.getU_pwd();
		String u_nickname = user.getU_nickname();
		String u_sex = user.getU_sex();
		String u_position = user.getU_position();

		String strSQL = "insert into user values(null,?,?,?,?,?,?,'/MicroBlogbeta/face/default.jpg',null,null,null)";
		//建立数据库连接
		DBConn dbConn = new DBConn();
		//执行数据库操作并保存结果集
		int affectedRows = dbConn.execOther(strSQL, new Object[] { u_email,u_pwd, u_email, u_nickname, u_sex, u_position});
		dbConn.closeConn();
		return affectedRows > 0 ? true : false;
	}

	public boolean checkUser(String u_name) {
		int i = 0;
		String strSQL = "select count(*) from user where u_name = ?";
		DBConn dbConn = new DBConn();
		ResultSet rs = dbConn.execQuery(strSQL, new Object[] { u_name });
		try {
			while (rs.next()) {
				i = rs.getInt(1);
			}
			return i > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbConn.closeConn();
		}
	}
}
