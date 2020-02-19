// JavaScript Document
var xhr;
function checkemail() {
	var email = document.getElementById("u_email").value;
	//1.创建XMLHttpRequest组建 
	xhr = new XMLHttpRequest();
	//3.初始化XMLHttpRequest组建 
	xhr.open("get", "/MicroBlogbeta/servlet/CheckNewRegisterNameServlet?u_name=" + email, true);
	//2.设置回调函数
	xhr.onreadystatechange = handler;
	//4.发送请求
	xhr.send(null);
}
//回调函数
function handler() {
	if (xhr.status == 200 && xhr.readyState == 4) {
		var res = document.getElementById("res");

		var str = xhr.responseText;
		var flag = eval('(' + str + ')');

		if (flag) {
			res.innerHTML = "此账号已经被占用";
		} else {
			var email = document.getElementById("u_email").value;
			if (email == "") {
				res.innerHTML = "账号不能为空";
			} else {
				res.innerHTML = "此账号可以使用";
			}
		}
	}
}


function checkpwd() {
	var u_pwd = document.getElementById("u_pwd");
	var u_rpwd = document.getElementById("u_rpwd");
	var pwdmsg = document.getElementById("pwdmsg");
	if (u_pwd.value != u_rpwd.value) {
		pwdmsg.innerHTML = "<img src='icon/err.png' align='absmiddle'> <font color='red'>两次输入不相同</font>";
	} else {
		if (u_pwd.value != null && u_pwd.value != "")
			pwdmsg.innerHTML = "<img src='icon/ok.png' align='absmiddle'> </font>";
	}
}


function checkForm() {
	if (document.getElementById("u_pwd").value == null
			|| document.getElementById("u_pwd").value == "") {
		alert("密码必须填写！");
		document.getElementById("u_pwd").focus();
		return false;
	}
	if (document.getElementById("u_nickname").value == null
			|| document.getElementById("u_nickname").value == "") {
		alert("昵称必须填写！");
		document.getElementById("u_nickname").focus();
		return false;
	}
	if (document.getElementById("u_position").value == null
			|| document.getElementById("u_position").value == "") {
		alert("所在地必须填写！");
		document.getElementById("u_position").focus();
		return false;
	}
}

function checkForm1() {
	if (document.getElementById("u_name1").value == null
			|| document.getElementById("u_name1").value == "") {
		alert("用户名必须填写！");
		document.getElementById("u_name1").focus();
		return false;
	}
	if (document.getElementById("u_pwd1").value == null
			|| document.getElementById("u_pwd1").value == "") {
		alert("密码必须填写！");
		document.getElementById("u_pwd1").focus();
		return false;
	}
}