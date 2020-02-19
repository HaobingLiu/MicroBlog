<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" type="text/css" href="css/global.css" />
		<link rel="stylesheet" type="text/css" href="css/mypassword.css" />
		<script type="text/javascript" src="script/pwd.js"></script>
		<title>微博 - 用户设置</title>

	</head>	
	<body>
	<!-- header开始-->
	<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20%" align="center"><img src="images/logo.png" width="178" height="62" /></td>
			<td width="55%" align="left">账号设置</td>
			<td width="25%" align="right">&nbsp;</td>
		</tr>
	</table>
	<!-- header结束-->

	<!-- container 开始-->
	<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
		<tr>
			<td width="670" valign="top">
				<form action="servlet/UpdatePwdServlet" method="post" name="pwdform">
				<!-- 记录被修改的标识 -->
				<input type="hidden" id="u_id" name="u_id" value="${u_id }" />
					<table border="0" align="center" cellpadding="0" cellspacing="0" id="userinfo">
						<tr><td class="title">个人资料</td></tr>
						<tr><td class="menu"><a href="userinfo.jsp">基本资料</a> | 修改密码 |<a href="myface.jsp">修改头像</a></td></tr>
						<tr>
							<td align="center">
								<table width="90%" border="0" cellpadding="5" cellspacing="0" id="userinfo_content">
									<tr>
										<td align="right">新密码：</td>
										<td><input name="nu_pwd" type="password" class="input1" id="nu_pwd" /></td>
									</tr>
									<tr>
										<td align="right">确认密码：</td>
										<td><input name="nu_rpwd" type="password" class="input1" id="nu_rpwd" onblur="checkpwd()" /></td>
									</tr>
									<tr>
										<td align="right">验证码：</td>
										<td>
											<input name="yzm" type="text" class="input2" id="yzm" />
											<img src="images/yzm.gif" width="70" height="25" />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="left"><input type="image" src="images/editbtn.png" width="150" height="37" onclick="document.pwdform.submit()" /></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr><td><a href="home.jsp?No=1">返回</a></td></tr>
					</table>
				</form>
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