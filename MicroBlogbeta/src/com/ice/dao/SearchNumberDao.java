package com.ice.dao;

import java.sql.ResultSet;

import com.ice.dbutil.DBConn;

public class SearchNumberDao {
//������ҳ������ʾ�ж�����ע����΢��
	public int SearchDao() {
		int count=0;
		String strSQl = "select count(*) from user";

		DBConn dbConn = new DBConn();
		//��������
		ResultSet rs = dbConn.execQuery(strSQl,new Object[]{});
		try {
			while(rs.next()){      //�������ѭ������
				count=rs.getInt(1);//����һ����¼
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