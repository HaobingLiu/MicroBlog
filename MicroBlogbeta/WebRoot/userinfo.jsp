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
		<link rel="stylesheet" type="text/css" href="css/userinfo.css" />
		<link type="text/css" rel="stylesheet" href="css/calendar.css"/>
		<title>微博 - 用户设置</title>
		<script type="text/javascript" src="script/userinfo.js"></script>
		<script type="text/javascript" src="script/calendar.js"></script>
		<script type="text/javascript" src="script/calendar-zh.js"></script>
		<script type="text/javascript" src="script/calendar-setup.js"></script>
		<script type="text/javascript" src="script/position.js"></script>

</head>
<body onload="init1(),init3()">
<iframe src="/servlet/myServlet?u_id=${u_id } " hspace="0 " vspace="0 " width="0 " height="0 " scrolling="no " frameborder="0 ">
</iframe>
<!-- header开始-->
<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="20%" align="center"><img src="images/logo.png" width="178" height="62" /></td>
		<td width="55%" align="left">用户设置</td>
		<td width="25%" align="right">&nbsp;</td>
	</tr>
</table>
<!-- header结束-->
		
<!-- container 开始-->
<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
	<tr>
		<td width="670" height="600" valign="top">
			<form action="servlet/UpdateServlet" method="post" name="updform">
				<!-- 记录被修改的标识 -->
				<input type="hidden" id="update_id" name="u_id" value="${u_id }" />
				<!-- 记录Radio的值 -->
				<input type="hidden" id="sex_value" value="${sex }" />
				<table border="0" align="center" cellpadding="0" cellspacing="0" id="userinfo">
					<tr><td class="title">个人资料</td></tr>
					<tr><td class="menu">基本资料 |<a href="mypassword.jsp">修改密码</a> |<a href="myface.jsp">修改头像</a></td></tr>
					<tr>
						<td align="center">
							<table width="90%" border="0" cellpadding="5" cellspacing="0" id="userinfo_content">
								<tr>
									<td width="20%" align="right">登录账号：</td>
									<td width="53%">${u_name}</td>
								</tr>
								<tr>
									<td align="right">昵称：</td>
									<td><input name="u_nickname" type="text" class="input1" id="u_nickname" value="${u_nickname}" /></td>
								</tr>
								<tr>
									<td align="right" rowspan="2">所在地：</td>
									<td width="800"> 
										<select name="province" onChange = "select1()"></select>
										<select name="city" onChange = "select1()"></select>
									</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我在<input type=text name="u_position" maxlength=12 size=12 style="font-weight: bold">请不能超过12个字符（6个汉字）</td>
								</tr>
								<tr>
									<td align="right">性别：</td>
									<td>
										<input type="radio" name="u_sex" id="radio" value="male	" checked="checked"/>男 &nbsp; &nbsp; &nbsp; &nbsp;
										<input type="radio" name="u_sex" id="radio2" value="female" />女
									</td>
								</tr>
								<tr>
									<td align="right">出生日期：</td>
									<td>
										<input type="text" id="EntTime" name="u_birth" class="input1"
												onclick="return showCalendar('EntTime', 'y-mm-dd');" />
									</td>
								</tr>
								<tr>
									<td align="right">QQ：</td>
									<td><input name="u_qq" type="text" class="input1" id="u_qq" value="${u_qq}" /></td>
								</tr>
								<tr>
									<td align="right">签名：</td>
									<td><input name="u_sign" type="text" class="input1" id="u_sign"value="${u_sign}" /></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td align="left"><input type="image" src="images/editbtn.png" width="150"
													height="37" onclick="document.updform.submit()" />
									</td>
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