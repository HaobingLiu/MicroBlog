<%@ page language="java" import="java.util.*,com.ice.dao.RegisterDao" pageEncoding="utf-8"%>
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
		<link rel="stylesheet" type="text/css" href="css/register.css" />
		<title>微博 - 登录失败</title>
		<script type="text/javascript" src="script/register.js"></script>
		<script type="text/javascript" src="script/position.js"></script>
	</head>
	
	<body onload="init()">
		<!-- header开始-->
		<table id="header" align="center" border="0" cellspacing="0" cellpadding="0">
			<tr><td width="20%" align="center">
					<img src="images/logo.png" width="180" height="62" />
				</td>
				<td width="55%" align="left">
					登录或注册失败
				</td>
				<td width="25%" align="right">
					&nbsp;
				</td>
			</tr>
		</table>
		<!-- header结束-->
		<!-- container 开始-->
		<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
			<tr><td width="670" valign="top">
					<form action="servlet/RegisterServlet" name="regform" method="post" onsubmit="return checkForm()">
						<table border="0" align="center" cellpadding="0" cellspacing="0"
							id="register">
							<tr>
								<td class="title">
									登录失败的话请确认账号密码是否正确</br>注册失败的话请确认账号可用并且两次输入密码一致
								</td>
							</tr>
							<tr>
								<td class="title">
									开通微博
								</td>
							</tr>
							<tr>
								<td>
									<table width="90%" border="0" cellpadding="5" cellspacing="0"
										id="register_content">
										<tr>
											<td width="20%" align="right">
												我的邮箱:
											</td>
											<td width="53%">
												<input name="u_email" type="text" class="input1"
													id="u_email" onblur="checkemail()" />
											</td>
											<td width="27%">
												 <span id="res">&nbsp;</span>
											</td>
										</tr>
									
										<tr>
											<td align="right">创建密码：</td>
											<td>
												<input name="u_pwd" type="password" class="input1" id="u_pwd" />
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td align="right">确认密码：</td>
											<td>
												<input name="u_rpwd" type="password" class="input1"  id="u_rpwd" onblur="checkpwd()" />
											</td>
											<td>
												<span id="pwdmsg"></span>
											</td>
										</tr>
										<tr>
											<td align="right">昵称：</td>
											<td>
												<input name="u_nickname" type="text" class="input1" id="u_nickname" />
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td align="right">性别：</td>
											<td>
												<input type="radio" name="u_sex" id="u_sex" value="male" checked="checked" />男 &nbsp; &nbsp; &nbsp; &nbsp;
												<input type="radio" name="u_sex" id="u_sex" value="female" />女
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td align="right" rowspan="2">
												所在地：
											</td>
											<td width="800">
												<select name="province" onchange="select()"></select>
												<select name="city" onchange="select()"></select>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												我在
												<input type=text name="u_position" maxlength=12 size=12
													id="u_position" style="font-weight: bold"/>
													请不能超过12个字符（6个汉字） 
											</td>
										</tr>
										<tr>
											<td align="right">验证码：</td>
											<td>
												<input name="yzm" type="text" class="input2" id="yzm" />
												<img src="images/yzm.gif" width="70" height="25" />
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;
											</td>
											<td align="left">
												<input type="submit" value="立即开通" />&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="index.jsp">返回</a>
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</form>
				</td>
				<td width="280" align="center" valign="top" class="pageright">
					<!-- userlogin 开始-->
					<form id="form1" name="form1" method="post" action="servlet/LoginServlet" onsubmit="return checkForm1()">
						<table id="login" width="220" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="2" class="title">
									已有微博登录账号？
								</td>
							</tr>
							<tr>
								<td colspan="2">
									邮箱：
									<br /><br />
									<input name="u_name" type="text" class="logininput"
										id="u_name1" onmouseover="this.focus()" />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									输入你的登录密码：
									<br /><br />
									<input name="u_pwd" type="password" class="logininput"
										id="u_pwd1" onmouseover="this.focus()" />
								</td>
							</tr>
							
							<tr>
								<td colspan="2" align="center">
									<input name="Submit" type="submit" class="loginbtn" id="Submit"
										value="  登录微博  " />
								</td>
							</tr>
						</table>
					</form>
					<!-- userlogin 结束-->
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
