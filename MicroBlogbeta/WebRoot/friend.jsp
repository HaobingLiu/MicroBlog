<%@ page language="java" import="java.util.*,com.ice.dao.*" pageEncoding="UTF-8"%>
<%@ page import="com.ice.po.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/home.css" />
<title>微博 - ${u_nickname}的关注</title>
 <%
 	User user=(User)session.getAttribute("user");
   	//关注
  	SearchFocusDao dao = new SearchFocusDao();
  	int SFCount =dao.SFDao(user.getU_id());
  	pageContext.setAttribute("SFCount",SFCount);
  	//粉丝
  	SearchFunNumberDao sfdao = new SearchFunNumberDao();
  	int SFunCount =sfdao.FunDao(user.getU_id());
  	pageContext.setAttribute("SFunCount",SFunCount);
  	//微博
  	FindMoodNumDao wbdao = new FindMoodNumDao();
  	int SMNCount = wbdao.getMyNum(user.getU_id());
  	pageContext.setAttribute("SMNCount",SMNCount); 
  	//分页
  	FindMoodNumDao findOrder=new FindMoodNumDao();
  	
  	int pageNo=0;
  	if(request.getParameter("No")==""){
  		pageNo=1;//默认是第一页
  	}
  	else{
  	 	pageNo=Integer.parseInt(request.getParameter("No"));
  	}	 
  	//要显示的微博
  	ShowMoodDao showmooddao=new ShowMoodDao();
  	List<Mood> lstFMod=showmooddao.showFriendsMood(user.getU_id(),pageNo,8);//showFriendsMood
  	pageContext.setAttribute("lstFMod",lstFMod);
  	//要推荐的好友，为好友的好友
  	RandomShowFavUser rsfu=new RandomShowFavUser();  
  	List<User>lstuser=rsfu.ShowFavUser(user.getU_id());
  	List<User>countLstUser = rsfu.RandomUser(lstuser,2); 
  	pageContext.setAttribute("countLstUser",countLstUser);
 %>
<%
  if(request.getParameter("msg")!=null)
  {
   int res=Integer.parseInt(request.getParameter("msg"));
   switch(res)
   {
     case 7:
     out.print("<script>alert('添加好友成功！'); </script>");
     break;
     case 8:
     out.print("<script>alert('添加好友失败，请检查对方是否已经是您的好友！'); </script>");
     
   }
  }
%>
</head>
<body>
<!-- header开始-->
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
		<td width="20%" align="center"><img src="images/logo.png" width="178" height="62" /></td>
	    <td width="55%" align="right">
	      <table border="0" align="right" cellpadding="0" cellspacing="0" id="daohang">
	        <tr>
	          <td width="20%"><a href="home.jsp?No=1">我的首页</a></td>
	          <td width="20%"><a href="profile.jsp?No=1">我的微博</a></td>
	          <td width="20%"><a href="friend.jsp?No=1">我的关注</a></td>
	          <td width="20%"><a href="servlet/LogoutServlet">[ 退出 ]</a></td>
	        </tr>
	      </table>
	    </td>
	</tr>
</table>
<!-- header结束-->

<!-- container 开始-->
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
	<tr>
		<td width="670" height="600" valign="top">
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" id="curruser">
			  <tr>
				<td width="24%"><img src="${u_images }" width="120" height="120" class="userphoto" /></td>
				<td width="76%" valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" id="curruserdetail">
					  <tr>
						<td class="title">${u_nickname}</td>
					  </tr>
					  <tr>
						<td>
						<p>${u_position}<br />
						   ${u_sign}<br />
						   <a href="userinfo.jsp">修改基本资料</a> | <a href="myface.jsp">修改头像</a> | <a href="mypassword.jsp">修改密码</a>
						</p>
						</td>
					  </tr>
					</table>
				</td>
			  </tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="menu">
				<tr>
					<td>模糊搜索：</td>
					<td>
						<form id="form1" name="search" method="post" action="servlet/SearchServlet">
						  <select name="subsql">
									<option value="mb_content">微博内容</option>
									<option value="mb_user">用户名称</option>
						  </select>
						  <input name="textfield" type="text" class="input" id="textfield" />
						  <input name="u_id" value="${u_id }" type="hidden"/>
						  <input name="button" type="submit" class="btnsearch" id="button" value="搜索" />
						</form>
					</td>
				</tr>
			</table>
			<!-- weibo 开始-->
			<c:forEach items="${lstFMod}" var="lstFMod">
			<table id="weibo" width="90%" border="0" align="center" cellpadding="3" cellspacing="0">
				<tr>
					<td rowspan="3" align="center" valign="top">
						<img src="${lstFMod.m_u_images}" width="50" height="50" />
						<form  action="servlet/RemoveFriendsServlet?u_id=${u_id}&f_user_id=${lstFMod.u_id}" method="post" name="myformrf">
							<input name="button4" type="submit" class="btnguanzhu" id="button4" value="-关注"/>
						</form>	
					</td>
					<td width="88%" class="content">${lstFMod.u_m_name}<img src="icon/v.gif" width="11" height="10" align="middle" />：${lstFMod.m_content}</td>
				</tr>
				<tr>
					<td>
						<c:if test="${lstFMod.m_images!=null}">
						<img src="${lstFMod.m_images}"  width="120" height="120"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td height="25">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" id="weibo_status">
						  <tr>
							<td>${lstFMod.m_date}</td>
							<td align="right"><a href="servlet/DealTransmit?u_id=${u_id}&m_id=${lstFMod.m_id}">赞</a>(${lstFMod.m_transmit_num})</td>
						  </tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="50"></td>
					<td>
						<form id="formcomment" name="formcomment" method="post" action="servlet/DealCommentServlet">
						  <input name="textfield" type="text" id="textfield" style="width:456px"/>
						  <input name="u_id" value="${u_id }" type="hidden"/>
						  <input name="m_id" value="${lstFMod.m_id }" type="hidden"/>
						  <input name="button6" type="submit" id="button6" value="评论" />
						</form>
						<form id="formshowcomment" name="formshowcomment" method="post" action="servlet/getCommentServlet?m_id=${lstFMod.m_id }">
				
						  <input name="button7" type="submit" id="button7" value="显示评论" />
						</form>
					</td>
			
				</tr>
			</table>
			</c:forEach>
		
			<% int all=(findOrder.getFriendsNum(user.getU_id())-1)/8+1; %>
			<table align="right"> 
				<tr>
					<td colspan="2" align="center">共<%=all %>页&nbsp;&nbsp;当前位于第<%=pageNo %>页
					<% if(pageNo>1){ %>
						<a href="friend.jsp?u_id=<%= user.getU_id() %>&No=<%=pageNo-1 %>">上一页</a>
					<%}
					if(pageNo<all){%>
						<a href="friend.jsp?u_id=<%= user.getU_id() %>&No=<%=pageNo+1 %>">下一页</a>
					<%}%>
					</td>  
				</tr>
			</table>
			<!-- weibo 结束-->
		</td>
		<td width="280" align="center" valign="top" class="pageright">
			<!-- userinfo 开始-->
			<table align="center" id="userinfo">
				<tr>
					<td colspan="2" align="left">
						<table width="80%" border="0" align="left" cellpadding="3" cellspacing="0">
						  <tr>
							<td align="center" class="split2">关注<br>${SFCount}</td>
							<td align="center" class="split2">粉丝<br>${SFunCount}</td>
							<td align="center" class="split2">微博<br>${SMNCount}</td>
						  </tr>
						</table>
					</td>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0" id="userlist">
				<tr>
					<td class="title" height="29">可能感兴趣的人</td>
					<td align="right" class="title"><a href="friend.jsp?No=<%=pageNo %>">[换一换]</a></td>
				</tr>
				<c:forEach items="${countLstUser}" var="lstuser">
				<form  action="servlet/AddFriendsServlet?u_id=${u_id}&f_user_id=${lstuser.u_id}" method="post" name="myform">
					<tr>
						<td colspan="2">
							<table border="0" cellpadding="0" cellspacing="0" class="userdetail">
								<tr>
									<td width="26%"><img src="${lstuser.u_images}" width="50" height="50" border="0" /></td>
									<td width="74%">用户：${lstuser.u_name}<input name="button3" type="submit" class="btnguanzhu" id="button3" value="+关注"/>
									  <br />昵称：${lstuser.u_nickname}
									  <br />性别：${lstuser.u_sex}
									  <br />所在地：${lstuser.u_position}
									  <c:if test="${lstuser.u_sign!=null}">
									  <br />签名：${lstuser.u_sign}
									  </c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</form>	
				</c:forEach>
			</table>
			<!-- userinfo 结束-->
		</td>
	</tr>
</table>
<!-- container 结束-->

<!--footer开始-->
<table id="footer" border="0" align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td width="447" align="center">Copyright &copy; 2016 刘皓冰. All Rights Reserved</td>
  </tr>   
</table>
<!--footer结束-->
</body>
</html>