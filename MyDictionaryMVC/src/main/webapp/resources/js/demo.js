// login --------------------------------------------------------------------------------
function onSignIn(googleUser) {
	var idTokenObj = new Object();
	idTokenObj.idToken = googleUser.getAuthResponse().id_token;
	$.ajax({
		url : 'http://localhost:8080/ajou/user/login',
		type : 'post',
		contentType : 'application/json;charset=UTF-8',
		dataType : 'json',
		data : JSON.stringify(idTokenObj),
		success : function(result, status) {
			// console.log("result: "+result.id + ", status: "+status);
			var userInfo = new Object();

			userInfo.index = result.index;
			userInfo.userId = result.userId;
			userInfo.name = result.name;
			userInfo.email = result.email;
			userInfo.pictureURL = result.pictureURL;

			if (typeof (Storage) !== "undefined") {
				localStorage.setItem("userInfo", JSON.stringify(userInfo));
			} else {
				console.log("Sorry, your browser does not support Web Storage...");
			}
		}
	});
}

function signOut() {
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function() {
		localStorage.setItem("userInfo", null);
		localStorage.setItem("friendList", null);
		alert('User ' + userInfo.name + ' signed out.');
		userInfo = new Object();
	});
}

// friend
// -------------------------------------------------------------------------------
function getFriendList() {

	var userInfo = JSON.parse(localStorage.getItem("userInfo"));

	if (userInfo.index != null) {

		var friendModel = new Object();

		friendModel.userIndex = userInfo.index;

		$.ajax({
			url : 'http://localhost:8080/ajou/friend/getFriendsByUserIndex',
			type : 'post',
			contentType : 'application/json;charset=UTF-8',
			dataType : 'json',
			data : JSON.stringify(friendModel),
			success : function(result, status) {
				// console.log(JSON.stringify(result));
				if (typeof (Storage) !== "undefined") {
					localStorage.setItem("friendList", JSON.stringify(result));
					makeFriendListHtml();
				} else {
					console.log("Sorry, your browser does not support Web Storage...");
				}
			}
		});
	}
}

function makeFriendListHtml() {

	var friendList = JSON.parse(localStorage.getItem("friendList"));

	for (var i = 0; i < friendList.length; i++) {
		var user = friendList[i];
		var listHtml = '<li class="list-group-item">'
				+ '<strong>Name</strong> ' + user.name
				+ '   <strong>email</strong> ' + user.email
				+ ' <button class="btn btn-default" onclick="deleteFriend('
				+ user.index + ')">delete</button></li>'
		//console.log($("#friendList"));
		$("#friendList").append(listHtml);
	}
}

function deleteFriend(index) {

	var userInfo = JSON.parse(localStorage.getItem("userInfo"));

	if (userInfo.index != null) {

		var friendModel = new Object();

		friendModel.userIndex = userInfo.index;
		friendModel.friendIndex = index;
		
		$.ajax({
			url : 'http://localhost:8080/ajou/friend/unfollowFriend',
			type : 'post',
			contentType : 'application/json;charset=UTF-8',
			dataType : 'json',
			data : JSON.stringify(friendModel),
			success : function(result, status) {
				console.log(result);
			}
		});
	}
}

function addFriend(index) {

	var userInfo = JSON.parse(localStorage.getItem("userInfo"));

	if (userInfo.index != null) {

		var friendModel = new Object();

		friendModel.userIndex = userInfo.index;
		friendModel.friendIndex = index;
		
		$.ajax({
			url : 'http://localhost:8080/ajou/friend/followFriend',
			type : 'post',
			contentType : 'application/json;charset=UTF-8',
			dataType : 'json',
			data : JSON.stringify(friendModel),
			success : function(result, status) {
				console.log(result);
			}
		});
	}
}

function searchFriends() {
	var searchEmail = $("#searchFriendByEmail").val();
	$("#searchFriendByEmail").val(null);
	console.log(searchEmail);
	
	var userInfo = JSON.parse(localStorage.getItem("userInfo"));

	if (userInfo.index != null) {

		var userModel = new Object();

		userModel.email = searchEmail;
		
		$.ajax({
			url : 'http://localhost:8080/ajou/user/getUserByEmail',
			type : 'post',
			contentType : 'application/json;charset=UTF-8',
			dataType : 'json',
			data : JSON.stringify(userModel),
			success : function(result, status) {
				//console.log(result);
				if(result.index !== null) {
					var listHtml = '<li class="list-group-item">'
						+ '<strong>Name</strong> ' + result.name
						+ '   <strong>email</strong> ' + result.email
						+ ' <button class="btn btn-default" onclick="addFriend('
						+ result.index + ')">add</button></li>'
						//console.log($("#friendList"));
						$("#searchFriendList").append(listHtml);
				} else {
					var errorHtml = "There is no result for email '" + searchEmail + "'";
					$("#searchFriendList").append(errorHtml);
				}
			}
		});
	}
}

// move location
// ------------------------------------------------------------------------
function toHome() {
	window.location = "./home";
}

function toFriendService() {
	window.location = './friend';
}

function toDocumentService() {
	window.location = './document';
}

function toSearchService() {
	window.location = './search';
}