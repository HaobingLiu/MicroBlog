function checkForm() {
	if (document.getElementById("u_name").value == null || document.getElementById("u_name").value == "") {
		alert("用户名必须填写！");
		document.getElementById("u_name").focus();
		return false;
	}
	if (document.getElementById("u_pwd").value == null || document.getElementById("u_pwd").value == "") {
		alert("密码必须填写！");
		document.getElementById("u_pwd").focus();
		return false;
	}
}