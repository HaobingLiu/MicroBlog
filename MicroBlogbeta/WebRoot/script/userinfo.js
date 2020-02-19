/**
 * 
 */
function init3() {
	var hidden_sex = document.getElementById('sex_value').value;
	var updform_sex = document.updform.sex; 
	for ( var i = 0; i < updform_sex.length; i++) {
		if (updform_sex[i].value == hidden_sex) { 
			updform_sex[i].checked = true; break;
		}
	}
}

function checkForm() {
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