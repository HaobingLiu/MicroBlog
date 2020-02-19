package com.ice.dao;

import java.sql.ResultSet;

import com.ice.dbutil.DBConn;

public class SearchNumberDao {
//用于首页上面显示有多少人注册了微博
	public int SearchDao() {
		int count=0;
		String strSQl = "select count(*) from user";

		DBConn dbConn = new DBConn();
		//保存结果集
		ResultSet rs = dbConn.execQuery(strSQl,new Object[]{});
		try {
			while(rs.next()){      //这里必须循环遍历
				count=rs.getInt(1);//返回一条记录
			}
			return count;

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			dbConn.closeConn();
		}
		return 0;

	}

}