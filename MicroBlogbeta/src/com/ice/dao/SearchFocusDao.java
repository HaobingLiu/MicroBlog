package com.ice.dao;

import java.sql.ResultSet;

import com.ice.dbutil.DBConn;

public class SearchFocusDao {

	//��ע������ѯ
	public int SFDao(int u_id) {
		int count = 0;
		String sql = "select count(*) from friends where u_id=?";
		DBConn conn = new DBConn();
		//��������
		ResultSet rs = conn.execQuery(sql, new Object[] {u_id});
		try {
			while(rs.next()){      //�������ѭ������
				count = rs.getInt(1);//����һ����¼
			}
			return count;
		} 
		catch (Exception e) {
		
		}finally {
			conn.closeConn();
		}return 0;
	}
}
