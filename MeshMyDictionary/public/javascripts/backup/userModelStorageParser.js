function getUserModelToStorage() {
	var userModel;
	if (typeof (Storage) !== "undefined") {
		userModel = JSON.parse(localStorage.getItem("userModel"));
	} else {
		alert("Sorry, your browser does not support Web Storage...");
	}
	return userModel;
}

function setUserModelToStorage(userModel) {
	if (typeof (Storage) !== "undefined") {
		localStorage.setItem("userModel", JSON.stringify(userModel));
	} else {
		alert("Sorry, your browser does not support Web Storage...");
	}
}

function cleanStorage() {
	localStorage.setItem("userModel", null);
}