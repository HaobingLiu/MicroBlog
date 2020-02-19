<%@ page language="java" import="java.util.*,com.ice.dao.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" type="text/css" href="css/global.css" />
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<title>微博系统</title>
		<script type="text/javascript" src="script/global.js"></script>
		<script type="text/javascript" src="script/index.js"></script>
		
	</head>

	<body id="body">
		<%
			SearchNumberDao dao = new SearchNumberDao();
			int count = dao.SearchDao();
		%>
		
		<table id="header" border="0" align="center" cellpadding="0" cellspacing="0">

		</table>
		<table border="0" align="center" width="1001px" cellpadding="0" cellspacing="0">
						<tr><td align="right" ><a href="about.jsp">关于微博</a></td></tr>
		</table>
		<!--header 结束-->
		<!--container 开始-->
		<table border="0" align="center" cellpadding="0" cellspacing="0" id="container">
			<tr><td width="70%" valign="top">
					<!-- content开始 -->
					<table id="content" border="0" cellspacing="0" cellpadding="0">
						<tr><td> <br/><br/><br/><br/><br/><br/><br/><br/><br/> </td></tr>
						<tr><td>
								<!--counter开始-->
								<table id="counter" border="0" cellpadding="0" cellspacing="0" align="center">
									<tr><td valign="middle">
											<table width="150" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr><td class="counternum"><%out.print(count);%></td></tr>
											</table>
									</td></tr>
								</table>
						</td></tr>
					</table>
					<!-- content结束 -->
			</td>
			<td>
				<!-- pageright开始 -->
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr><td align="center">
							<!--login开始-->
							<form id="form1" name="form1" method="post" action="servlet/LoginServlet" onsubmit="return checkForm()">
								<table id="login" width="220" border="0" cellspacing="0" cellpadding="0">
									<tr><td colspan="2">
											<a href="register.jsp"><img src="images/regbtn.png" width="200" height="37" border="0" /> </a>
									</td></tr>
									<%
										Cookie cookies[]=request.getCookies();
										String name=null;
										String password=null;
										if(cookies!=null){
											for(int i=0;i<cookies.length;i++)
											{
												if(cookies[i].getName().trim().equals("usercookie")){    
													name=cookies[i].getValue().split("-")[0];   
													password=cookies[i].getValue().split("-")[1];      
												}
											}
										}
									%>
									<tr><td colspan="2" align="left">邮箱：
											<br />
											<input name="u_name" type="text" class="logininput" id="u_name" onmouseover="this.focus()" value="${name}"/>
									</td></tr>
									<tr><td colspan="2" align="left">输入你的登录密码：
											<br />
											<input name=u_pwd type="password" class="logininput" id="u_pwd" onmouseover="this.focus()" value="${password}"/>
									</td></tr>
							
									<tr><td colspan="2" align="center">
											<input name="Submit" type="submit" class="loginbtn" id="Submit" value="  登录微博  " />
									</td></tr>
								</table>
							</form>
							<!--login结束-->
					</td></tr>
					<tr><td height="20" valign="bottom"><%-- 横条 --%>
							<hr color="#CCCCCC" width="97%" size="1" />
					</td></tr>
					<tr><td>
							<!-- messge 开始-->
							<table id="message" width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr><td class="title">公告</td></tr>
								<tr><td>
										<ul type="disc"><%-- 无序 HTML 列表  --%>
											<li>微博系统开 始测试火爆进行中......</li>
											<li>邮箱即是你的账号，方便易记！</li>
											<li>有啥新鲜事 ，跟大家说说~</li>
											<li>完善资料寻 找志同道合的朋友</li>
										</ul>
								</td></tr>
							</table>
					</td></tr>
				</table>
				<!-- pageright结束 -->
			</td></tr>
		</table>
		<!--container 结束-->
		<!--footer开始-->
		<table id="index_footer" border="0" align="center" cellpadding="5" cellspacing="0">
			<tr><td width="350" height="50" align="center">Copyright &copy; 2016 刘皓冰. All Rights Reserved
			</td></tr>
		</table>
		<!--footer结束-->
	</body>
</html>
