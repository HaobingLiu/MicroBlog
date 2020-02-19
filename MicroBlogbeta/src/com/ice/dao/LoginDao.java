package com.ice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ice.dbutil.DBConn;

public class LoginDao {
    //登陆验证
	public int checkUser(final String u_name, final String u_pwd) {
		int res=0;
		String strSQL = "select * from user where u_name = ? and u_pwd = ?";
		//建立数据库连接
		DBConn dbConn = new DBConn();
		//保存结果集
		ResultSet rs = dbConn.execQuery(strSQL, new Object[] { u_name, u_pwd });
		try {
			//最初rest的游标位于第一行之前，因此第一次调用next()，将把游标置于第一行上，使它成为当前行。随着每次调用 next()，导致游标向下移动一行，按照从上至下的次序获取 ResultSet行。
			//如果登陆成功，返回用户id
			while(rs.next()){      //这里必须循环遍历
				res=rs.getInt("u_id");//返回一条记录
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			//登陆失败返回-1
			return -1;
		} finally {
			dbConn.closeConn();
		}
	}
	//得到用户昵称
	public String checkUserNickname(final int u_id) {
		String res="";
		String strSQL = "select * from user where u_id = ?";
		//建立数据库连接
		DBConn dbConn = new DBConn();
		//保存结果集
		ResultSet rs = dbConn.execQuery(strSQL, new Object[] { u_id });
		try {
           //返回用户昵称
			while(rs.next()){      //这里必须循环遍历
				res=rs.getString("u_nickname");//返回一条记录
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			//如果未能成功获得，则返回null
			return null;
		} finally {
			//关闭数据库连接
			dbConn.closeConn();
		}
	}
//得到用户的所在地
	public String checkUserPosition(final int u_id) {
		String res="";
		String strSQL = "select * from user where u_id = ?";
		//建立数据库连接
		DBConn dbConn = new DBConn();
		//保存结果集
		ResultSet rs = dbConn.execQuery(strSQL, new Object[] { u_id });
		try {
			//返回用户所在地
			while(rs.next()){      //这里必须循环遍历
				res=rs.getString("u_position");//返回一条记录
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
