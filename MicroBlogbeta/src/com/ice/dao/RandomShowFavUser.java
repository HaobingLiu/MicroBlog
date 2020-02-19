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
		//选择好友的好友用于随机显示，别忘了除去自己已关注的好友和自己~ 由于mysql不支持except，用left join实现
		String strSQL="select * from (select * from user where u_id in (select f_user_id from friends where u_id in (select f_user_id from friends where u_id= ? ))) u1   left join   (select * from user where u_id in (select f_user_id from friends where u_id= ? )  union  select * from user where u_id= ? ) u2 on u1.u_id=u2.u_id where u2.u_id is null";
		//链接数据库
		DBConn dbconn=new DBConn();
		ResultSet rs=dbconn.execQuery(strSQL, new Object[]{u_id,u_id,u_id});
		try {
			while(rs.next())
			{
				User user=new User();
				user.setU_id(rs.getInt("u_id"));
				//得到用户头像的路径
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
			    //将user对象添加到lstuser中
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
	//该函数用于随机显示可能感兴趣的人
	public List<User> RandomUser(List<User> list, int count) {
		List<User> lstUser = new ArrayList<User>();
		if(count < list.size()){//要显示的数量count 要小
			int i = 0;
			Random ran = new Random();
			while(i < count) {
				User user = list.get(ran.nextInt(list.size()));//Random().nextInt(int n)该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n
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
