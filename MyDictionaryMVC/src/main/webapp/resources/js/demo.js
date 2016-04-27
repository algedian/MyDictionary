var hostURL = 'http://dijkstra.ajou.ac.kr:8080/MyDictionaryMVC/';

// login --------------------------------------------------------------------------------
function onSignIn(googleUser) {
	var idTokenObj = new Object();
	idTokenObj.idToken = googleUser.getAuthResponse().id_token;
	$
			.ajax({
				url : hostURL+'user/login',
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
						localStorage.setItem("userInfo", JSON
								.stringify(userInfo));
					} else {
						console
								.log("Sorry, your browser does not support Web Storage...");
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

		$
				.ajax({
					url : hostURL+'friend/getFriendsByUserIndex',
					type : 'post',
					contentType : 'application/json;charset=UTF-8',
					dataType : 'json',
					data : JSON.stringify(friendModel),
					success : function(result, status) {
						// console.log(JSON.stringify(result));
						if (typeof (Storage) !== "undefined") {
							localStorage.setItem("friendList", JSON
									.stringify(result));
							makeFriendListHtml();
						} else {
							console
									.log("Sorry, your browser does not support Web Storage...");
						}
					}
				});
	}
}

function deleteFriend(index) {

	var userInfo = JSON.parse(localStorage.getItem("userInfo"));

	if (userInfo.index != null) {

		var friendModel = new Object();

		friendModel.userIndex = userInfo.index;
		friendModel.friendIndex = index;

		$
				.ajax({
					url : hostURL+'friend/unfollowFriend',
					type : 'post',
					contentType : 'application/json;charset=UTF-8',
					dataType : 'text',
					data : JSON.stringify(friendModel),
					success : function(result, status) {
						console.log(result);
						if (result == "success") {
							window.location.reload();
						} else {
							alert("cannot delete friend");
							window.location.reload();
						}
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

		$
				.ajax({
					url : hostURL+'friend/followFriend',
					type : 'post',
					contentType : 'application/json;charset=UTF-8',
					dataType : 'text',
					data : JSON.stringify(friendModel),
					success : function(result, status) {
						if (result == "success") {
							window.location.reload();
						} else {
							alert("cannot add friend");
							window.location.reload();
						}
					}
				});
	}
}

function searchFriend() {
	var searchEmail = $("#searchFriendByEmail").val();
	$("#searchFriendByEmail").val(null);
	// console.log(searchEmail);

	var userInfo = JSON.parse(localStorage.getItem("userInfo"));

	if (userInfo.index != null) {

		var userModel = new Object();

		userModel.email = searchEmail;

		$
				.ajax({
					url : hostURL+'user/getUserByEmail',
					type : 'post',
					contentType : 'application/json;charset=UTF-8',
					dataType : 'json',
					data : JSON.stringify(userModel),
					success : function(result, status) {
						// console.log(result);
						if (result.index !== null) {
							var listHtml = '<li class="list-group-item">'
									+ '<strong>Name</strong> '
									+ result.name
									+ '   <strong>email</strong> '
									+ result.email
									+ ' <button class="btn btn-default" onclick="addFriend('
									+ result.index + ')">add</button></li>'
							// console.log($("#friendList"));
							$("#searchFriendList").append(listHtml);
						} else {
							alert("There is no result for email '"
									+ searchEmail + "'");
						}
					}
				});
	}
}

// document
// ------------------------------------------------------------------------------

function createDocument() {
	var userInfo = JSON.parse(localStorage.getItem("userInfo"));

	if (userInfo.index != null) {
		setDocument(userInfo.userId, $("#newKeyword").val(), $("#newDocument")
				.val());
		$("#newKeyword").val(null);
		$("#newDocument").val(null);
		window.location.reload();
	}
}
function saveDocument(keyword) {

	var userInfo = JSON.parse(localStorage.getItem("userInfo"));

	if (userInfo.index != null) {
		setDocument(userInfo.userId, keyword, $("#userDocumentContent").val());
	}
}
function setDocument(userId, keyword, document) {

	var documentModel = new Object();

	documentModel.userId = userId;
	documentModel.keyword = keyword;
	documentModel.document = document;

	$
			.ajax({
				url : hostURL+'document/setDocument',
				type : 'post',
				contentType : 'application/json;charset=UTF-8',
				dataType : 'text',
				data : JSON.stringify(documentModel),
				success : function(result, status) {
					// console.log(result);
					if (result == "success") {
						window.location.reload();
					} else {
						alert("cannot delete the document");
						// window.location.reload();
					}
				}
			});
}

function deleteDocument(keyword) {
	var userInfo = JSON.parse(localStorage.getItem("userInfo"));

	if (userInfo.index != null) {
		var documentModel = new Object();

		documentModel.userId = userInfo.userId;
		documentModel.keyword = keyword;

		$
				.ajax({
					url : hostURL+'document/deleteDocument',
					type : 'post',
					contentType : 'application/json;charset=UTF-8',
					dataType : 'text',
					data : JSON.stringify(documentModel),
					success : function(result, status) {
						// console.log(result);
						if (result == "success") {
							window.location.reload();
						} else {
							alert("cannot delete the document");
							// window.location.reload();
						}
					}
				});
	}
}

// search
// --------------------------------------------------------------------------------
function searchDocument() {
	var keyword = $("#searchDocumentsByKeyword").val();
	$("#searchDocumentsByKeyword").val(null);
	searchDocumentbyKeyword(keyword);
}

function searchDocumentbyKeyword(keyword) {
	var userInfo = JSON.parse(localStorage.getItem("userInfo"));

	if (userInfo.index != null) {
		var searchModel = new Object();
		searchModel.userIndex = userInfo.index;
		searchModel.userId = userInfo.userId;
		searchModel.keyword = keyword;

		getUserDocument(searchModel);
		/*
		 * in case of getting search result from friends
		 */
		getDocumentsOfFriends(searchModel);
	}
}

function getUserDocument(searchModel) {
	var userDocument = new Object();
	$
			.ajax({
				url : hostURL+'search/getUserDocument',
				type : 'post',
				contentType : 'application/json;charset=UTF-8',
				dataType : 'json',
				data : JSON.stringify(searchModel),
				success : function(result, status) {
					// console.log(result);
					makeUserDocumentHtml(result);
				}
			});
}

function getDocumentsOfFriends(searchModel) {
	$
			.ajax({
				url : hostURL+'search/getFriendDocuments',
				type : 'post',
				contentType : 'application/json;charset=UTF-8',
				dataType : 'json',
				data : JSON.stringify(searchModel),
				success : function(result, status) {
					// console.log(result);
					makeFriendDocumentListHtml(result);
				}
			});
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

// building UI
// -------------------------------------------------------------------------
function makeFriendListHtml() {

	var friendList = JSON.parse(localStorage.getItem("friendList"));

	for (var i = 0; i < friendList.length; i++) {
		var user = friendList[i];
		var listHtml = '<li class="list-group-item">'
				+ '<strong>Name</strong> ' + user.name
				+ '   <strong>email</strong> ' + user.email
				+ ' <button class="btn btn-default" onclick="deleteFriend('
				+ user.index + ')">delete</button></li>'
		// console.log($("#friendList"));
		$("#friendList").append(listHtml);
	}
}
var keyword = null;
function makeUserDocumentHtml(userDocument) {
	keyword = userDocument.keyword;
	if (keyword != null) {
		var documentHtml = '<div class="panel-heading">'
				+ '<h3 class="panel-title">'
				+ userDocument.keyword
				+ '</h3>'
				+ '<button class="btn btn-link btn-xs" type="button" data-toggle="modal" data-target="#userDocument">edit</button>'
				+ '<button class="btn btn-link btn-xs" type="button" onclick="deleteDocument(keyword);">delete</button>'
				+ '<!-- User Document Modal -->'
				+ '<div class="modal fade" id="userDocument" tabindex="-1 role="dialog" aria-labelledby="userDocumentKeyword">'
				+ '<div class="modal-dialog" role="document">'
				+ '<div class="modal-content">'
				+ '<div class="modal-header">'
				+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
				+ '<span aria-hidden="true">&times;</span></button>'
				+ '<h4 class="modal-title" id="userDocumentKeyword">'
				+ userDocument.keyword
				+ '</h4>'
				+ '</div>'
				+ '<div class="modal-body">'
				+ '<textarea class="form-control" id="userDocumentContent">'
				+ userDocument.document
				+ '</textarea>'
				+ '</div>'
				+ '<div class="modal-footer">'
				+ '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'
				+ '<button type="button" class="btn btn-primary" onclick="saveDocument(keyword);">Save</button>'
				+ '</div>'
				+ '</div>'
				+ '</div>'
				+ '</div><!-- User Document Modal -->'
				+ '</div>'
				+ '<div class="panel-body">'
				+ '<p>'
				+ userDocument.document
				+ '</p>'
				+ '<p>by user, created:'
				+ userDocument.createTime
				+ ', updated:' + userDocument.updateTime + '</p>' + '</div>';

		$("#searchedUserDocument").append(documentHtml);
	}
}

function makeFriendDocumentListHtml(friendsDocuments) {

	for (var i = 0; i < friendsDocuments.length; i++) {
		var fd = friendsDocuments[i];
		// console.log(fd);
		var friend = getFriendInfoByUserId(fd.userId);
		// console.log(friend);
		var documentListHtml = '<li class="list-group-item">'
				+ '<h4 class="list-group-item-heading">' + fd.keyword + '</h4>'
				+ '<p class="list-group-item-text">' + fd.document + '</p>'
				+ '<p class="list-group-item-text">by ' + friend.name + '('
				+ friend.email + '), created:' + fd.createTime + ', updated:'
				+ fd.updateTime + '</p>' + '</li>';

		$("#searchDocumentList").append(documentListHtml);
	}
}

// find from localStorage
function getFriendInfoByUserId(userId) {
	var friend = new Object();
	var friendList = JSON.parse(localStorage.getItem("friendList"));

	for (var i = 0; i < friendList.length; i++) {
		if (friendList[i].userId == userId) {
			return friendList[i];
		}
	}
}
