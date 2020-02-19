<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="css/global.css" />
<link rel="stylesheet" type="text/css" href="css/myface.css" />
<title>微博 - 用户设置</title>
<script type="text/javascript" src="script/myface.js"></script>
</head>
<body>
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
		<td width="670" valign="top">
			<form action="servlet/UploadFaceServlet" method="post" enctype="multipart/form-data">
			<!-- 此地记录传递过来的ID值 -->
			<input type="hidden" name="id" value="${u_id }"/>
				<table border="0" align="center" cellpadding="0" cellspacing="0" id="userinfo">
					<tr><td class="title">个人资料</td></tr>
					<tr><td class="menu"><a href="userinfo.jsp">基本资料</a> | <a href="mypassword.jsp">修改密码</a> | 修改头像</td></tr>
					<tr>
						<td align="center">
							<table width="90%" border="0" cellpadding="5" cellspacing="0" id="userinfo_content">
								<tr>
									<!-- 此地为动态更新头像用的 -->
									<td width="20%" align="center"><img src="${url}" width="120" height="120" /></td>
									<input type="hidden" name="u_id" value="${u_id }"/>
									<td width="53%">
										<p>
											<input type="file" name="u_images" id="fileField" />
											<input type="submit" name="button" id="button" value=" 保 存 " />
										</p>
										<p>请选择图片格式，且文件大小不超过2M的图片 </p>
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