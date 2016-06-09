function getUserModel() {
	var escape = "index:";
	var ca = document.cookie.split(';');
	
	if (ca.indexOf(escape) == 0) {
		return JSON.parse(ca);
	}
	return null;
}

function setUserModel(userModel) {
	var d = new Date();
	d.setTime(d.getTime() + (365 * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	
	if(userModel !== null) {
		document.cookie = userModel + "; " + expires;
	} else {
		document.cookie = " " + "; " + expires;
	}
}

function checkCookie() {
	var userModel = getUserModel();
	
	if (userModel !== null) {
		return userModel;
	}
	return null;
}

function removeCookie() {
	setUserModel(null, 365);
}