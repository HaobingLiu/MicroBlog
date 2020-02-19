package com.ice.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ice.dbutil.DBConn;
import com.ice.po.User;

public class RandomShowFavUser {
	public List<User> ShowFavUser(int u_id)
	{
		List<User> lstuser=new ArrayList<User>();
		//ѡ����ѵĺ������������ʾ�������˳�ȥ�Լ��ѹ�ע�ĺ��Ѻ��Լ�~ ����mysql��֧��except����left joinʵ��
		String strSQL="select * from (select * from user where u_id in (select f_user_id from friends where u_id in (select f_user_id from friends where u_id= ? ))) u1   left join   (select * from user where u_id in (select f_user_id from friends where u_id= ? )  union  select * from user where u_id= ? ) u2 on u1.u_id=u2.u_id where u2.u_id is null";
		//�������ݿ�
		DBConn dbconn=new DBConn();
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{u_id,u_id,u_id});
		try {
			while(rs.next())
			{
				User user=new User();
				user.setU_id(rs.getInt("u_id"));
				//�õ��û�ͷ���·��
				FaceDao facedao=new FaceDao();
				String getFaceUrl=facedao.getFaceUrl(rs.getInt("u_id"));
				
				user.setU_images(getFaceUrl);
				user.setU_name(rs.getString("u_name"));
				user.setU_nickname(rs.getString("u_nickname"));
				user.setU_sex(rs.getString("u_sex"));
				user.setU_position(rs.getString("u_position"));
				user.setU_sign(rs.getString("u_sign"));
				user.setU_qq(rs.getString("u_qq"));
				user.setU_birth(rs.getString("u_birth"));
			    //��user������ӵ�lstuser��
				lstuser.add(user);
			}
			return lstuser;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		finally{
			dbconn.closeConn();
		}
	}
	//�ú������������ʾ���ܸ���Ȥ����
	public List<User> RandomUser(List<User> list, int count) {
		List<User> lstUser = new ArrayList<User>();
		if(count < list.size()){//Ҫ��ʾ������count ҪС
			int i = 0;
			Random ran = new Random();
			while(i < count) {
				User user = list.get(ran.nextInt(list.size()));//Random().nextInt(int n)�÷���������������һ�������intֵ����ֵ����[0,n)�����䣬Ҳ����0��n֮������intֵ������0��������n
				if(lstUser.contains(user)) {
					continue;
				}else{
					lstUser.add(user);
					i++;
				}
			}
			
		}else{
			lstUser = list;
		}
		
		return lstUser;
	}

}
