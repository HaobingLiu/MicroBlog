package com.ice.dao;

import java.sql.ResultSet;

import com.ice.dbutil.DBConn;

public class SearchFocusDao {

	//关注数量查询
	public int SFDao(int u_id) {
		int count = 0;
		String sql = "select count(*) from friends where u_id=?";
		DBConn conn = new DBConn();
		//保存结果集
		ResultSet rs = conn.execQuery(sql, new Object[] {u_id});
		try {
			while(rs.next()){      //这里必须循环遍历
				count = rs.getInt(1);//返回一条记录
			}
			return count;
		} 
		catch (Exception e) {
		
		}finally {
			conn.closeConn();
		}return 0;
	}
}
