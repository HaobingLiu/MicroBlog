// JavaScript Document

function checkpwd() {
	var nu_pwd = document.getElementById("nu_pwd");
	var nu_rpwd = document.getElementById("nu_rpwd");
	if (nu_pwd.value != nu_rpwd.value) {
		alert("两次输入不相同！");
	} else {
		if (nu_pwd.value != null && nu_pwd.value != "")
			alert("新密码要记住哟，千万不要记混哟！");
	}
}