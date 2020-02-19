<%@ page language="java" import="java.util.*,com.ice.dao.*" pageEncoding="UTF-8"%>
<%@ page import="com.ice.po.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/home.css" />
<title>微博 - 搜索结果</title>
</head>
<body>
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
	 
	//要推荐的好友，为好友的好友
	RandomShowFavUser rsfu=new RandomShowFavUser();  
	List<User>lstuser=rsfu.ShowFavUser(user.getU_id());
	List<User>countLstUser = rsfu.RandomUser(lstuser,2); 
	pageContext.setAttribute("countLstUser",countLstUser);
%>
<!-- header开始-->
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
		<td width="20%" align="center"><img src="images/logo.png" width="178" height="62" /></td>
	    <td width="55%" align="right">
	      <table border="0" align="right" cellpadding="0" cellspacing="0" id="daohang">
	        <tr>
	          <td width="20%"><a href="home.jsp?No=1">我的首页</a></td>
	          <td width="20%"><a href="profile.jsp?No=1">我的微博</a></td>
	          <td width="20%"><a href="friend.jsp">我的关注</a></td>
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
						<option value="mb_user">用户</option>
					  </select>
					  <input name="textfield" type="text" class="input" id="textfield" />
					  <input name="u_id" value="${u_id }" type="hidden"/>
					  <input name="button" type="submit" class="btnsearch" id="button" value="搜索" />
					</form>
				</td>
			  </tr>
			</table>
			<!-- weibo 开始-->
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" id="curruser">
				<tr>
					<td>
						<c:forEach items="${lstsearch}" var="lstuser1">
						
						<form  action="servlet/AddFriendsServlet?u_id=${u_id}&f_user_id=${lstuser1.u_id}" method="post" name="myform">
							<tr>
								<td colspan="2">
									<table border="0" cellpadding="0" cellspacing="0" class="userdetail">
										<tr>
											<td width="26%"><img src="${lstuser1.u_images}" width="120" height="120" border="0" /></td>
											<td width="74%">用户：${lstuser1.u_name}<input name="button3" type="submit" class="btnguanzhu" id="button3" value="+关注"/>
											  <br />昵称：${lstuser1.u_nickname}
											  <br />性别：${lstuser1.u_sex}
											  <br />所在地：${lstuser1.u_position}
											  <c:if test="${lstuser1.u_sign!=null}">
											  <br />签名：${lstuser1.u_sign}
											  </c:if>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</form>
						</c:forEach>
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
							<td align="center" class="split2">关注<br>${SFunCount}</td>
							<td align="center" class="split2">粉丝<br>${SFCount}</td>
							<td align="center" class="split2">微博<br>${SMNCount}</td>
						  </tr>
						</table>
					</td>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0" id="userlist">
				<tr>
					<td class="title" height="29">可能感兴趣的人</td>
					<td align="right" class="title"><a href="showsearchbyuser.jsp">[换一换]</a></td>
				</tr>
				<c:forEach items="${countLstUser}" var="lstuser">
				<form  action="servlet/AddFriendsServlet?u_id=${u_id}&f_user_id=${lstuser.u_id}" method="post" name="myform">
					<tr>
						<td colspan="2"><table border="0" cellpadding="0" cellspacing="0" class="userdetail">
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